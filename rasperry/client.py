import  http.client as httplib
import requests
import subprocess
import wave
import json


def get_txt(data):
    return data.decode('utf-8')


def decode_audio(doc, name="output2.wav"):
    with open(name, "wb") as f:
        f.write(doc)
        f.close()
        print("File saved as " + name)
        return name


def init_client(address, port):
    return httplib.HTTPConnection(address, port)


def ping(c):
    c.request('POST', '/hello', '{}')
    return get_txt(c.getresponse().read())


def test_receive_audio(c, path):
    c.request('POST', '/audio', '{}')
    decode_audio(c.getresponse().read(), path)
    print("Audio file received")


def audio_to_text(c, address, audio):
    url = address+"/stt"
    files = {'audio_file': open(audio, 'rb')}
    r = requests.post(url, files=files)
    return r.text


def text_to_audio(c, text, speaker, lang):
    data = {
        'text': text,
        'speaker': speaker,
        'lang': lang
    }

    json_data = json.dumps(data)
    headers = {'Content-Type': 'application/json'}
    c.request('POST', '/tts', json_data, headers)

    temp = c.getresponse()
    code = temp.status 
    if code != 200:
        print("Error: " + str(code))
    else:
        return decode_audio(temp.read(), "response.wav")

def ollama(model="mistral", text="hello"):
    import requests

    json_data = {
        "model": model,
        "prompt": text
    }

    response = requests.post('http://localhost:11434/api/generate', json=json_data)
    res = response.text
    # to json
    print(type(res))
    res = res.split("\n")
    ok = res.pop()
    result = ""
    for r in res:
        print(r)
        temp = json.loads(r)
        result += temp["response"]

    return result


if __name__ == "__main__":
    c = init_client("localhost", 8080)

    ollama()

    c.close()