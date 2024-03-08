import  http.client as httplib
import subprocess
import wave


def get_txt(c):
    doc = c.getresponse().read().decode('utf-8')
    return doc


def decode_audio(c):
    doc = c.getresponse().read()
    with open("output2.wav", "wb") as f:
        f.write(doc)
        f.close()
        print("File saved")


c = httplib.HTTPConnection('localhost', 8080)
c.request('POST', '/hello', '{}')
print(get_txt(c))

c.request('POST', '/audio', '{}')
decode_audio(c)

c.close()
