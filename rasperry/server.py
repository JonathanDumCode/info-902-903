import subprocess
import wave
from bottle import run, post, request, response, get, route, static_file


@route('/hello',method = 'POST')
def return_audio():
    return "hello!"


@route('/audio',method = 'POST')
def return_audio():
    return static_file("output.wav", root='.')

run(host='localhost', port=8080, debug=True)