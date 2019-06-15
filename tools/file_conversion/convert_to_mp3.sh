#!/bin/bash
#
# Converts the files in a given directory, and its sub directories, from wav
# format into mp3 format.
#
# Author: Blair Butterworth
#

brew install ffmpeg
brew install rename

find . -name *.wav -execdir ffmpeg -i {} -codec:a libmp3lame -qscale:a 2 {}.mp3 \;
find . -name *.wav.mp3 -exec rename 's/.wav.mp3/.mp3/' '{}' \;
find . -name *.wav -exec rm -rf '{}' \;
