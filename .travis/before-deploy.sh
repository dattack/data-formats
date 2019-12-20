#!/usr/bin/env bash
openssl aes-256-cbc -K $encrypted_a8da13599b2d_key -iv $encrypted_a8da13599b2d_iv -in .travis/codesigning.asc.enc -out .travis/codesigning.asc -d
gpg --fast-import .travis/codesigning.asc
