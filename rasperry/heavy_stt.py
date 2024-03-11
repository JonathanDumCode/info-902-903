import torch
from transformers import AutoModelForSpeechSeq2Seq, AutoProcessor, pipeline
from datasets import load_dataset
import argparse


def init_heavy_stt():
    device = "cuda:0" if torch.cuda.is_available() else "cpu"
    torch_dtype = torch.float16 if torch.cuda.is_available() else torch.float32

    model_id = "openai/whisper-large-v3"

    model = AutoModelForSpeechSeq2Seq.from_pretrained(
        model_id, torch_dtype=torch_dtype, low_cpu_mem_usage=True, use_safetensors=True
    )
    model.to(device)

    processor = AutoProcessor.from_pretrained(model_id)

    pipe = pipeline(
        "automatic-speech-recognition",
        model=model,
        tokenizer=processor.tokenizer,
        feature_extractor=processor.feature_extractor,
        max_new_tokens=256,
        chunk_length_s=30,
        batch_size=16,
        return_timestamps=True,
        torch_dtype=torch_dtype,
        device=device,
    )

    return pipe


def run_heavy_stt(pipe, audio):
    sample = open(audio, "rb").read()
    return pipe(sample)["text"]


if __name__ == "__main__":
    parser = argparse.ArgumentParser()
    parser.add_argument("--audio_file", default="output.wav", type=str, help="The audio file to be converted to text")
    args = parser.parse_args()


    pipe = init_heavy_stt()
    print(run_heavy_stt(pipe, args.audio_file))