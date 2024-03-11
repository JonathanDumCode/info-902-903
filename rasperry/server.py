import subprocess
import wave
from bottle import run, post, request, response, get, route, static_file, Bottle
import random
import string
from tts import *
from stt import *
from colorama import init as colorama_init
from colorama import Fore
from colorama import Style

MAX_REQUEST_BODY_SIZE = 200 * 1024 * 1024
_tts = None
_stt = None


@route('/hello', method = 'POST')
def return_audio():
    return "hello!"


@route('/audio', method = 'POST')
def return_audio():
    return static_file("output.wav", root='.')


@route('/stt', method = 'POST')
def return_audio():
    print("STT")
    global _stt
    name = random_string(10) + ".wav"
    print(name)
    audio = request.files.get('audio_file')
    if audio:
        audio.save(name, overwrite=True)
        print("Saving as " + name)
        data = run_stt(_stt, name)
        return data
    else:
        return "No audio file received"


@route('/tts', method = 'POST')
def return_audio():
    global _tts

    try:
        json_data = request.json
        if json_data is not None:
            required_keys = {'text', 'speaker', 'lang'}
            if required_keys.issubset(json_data):
                text = json_data['text']
                speaker = json_data['speaker']
                lang = json_data['lang']
                name = random_string(10) + ".wav"
                print(name)
                run_tts(_tts, text, speaker, name, lang)
                return static_file(name, root='.')

            else:
                response.status = 400
                return "Missing required keys in JSON data"
        else:
            response.status = 400 
            return "Invalid or missing JSON data in the request body"
    except Exception as e:
        response.status = 500 
        return f"Error: {str(e)}"


def random_string(length):
    return ''.join(random.choice(string.ascii_letters) for m in range(length))


def init_globals():
    global _tts
    global _stt
    print("Initializing TTS and STT")
    _tts = init_tts()
    _stt = init_stt()
    print("{Fore.GREEN}TTS and STT initialized{Style.RESET_ALL}")


if __name__ == '__main__':

    colorama_init()
    init_globals()
    run(host='localhost', port=8080, debug=True, server_max_request_body_size=MAX_REQUEST_BODY_SIZE)