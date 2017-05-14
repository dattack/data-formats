#!/usr/bin/env bash
if [ "$TRAVIS_BRANCH" = 'master' ] && [ "$TRAVIS_PULL_REQUEST" == 'false' ]; then
    openssl aes-256-cbc -K $encrypted_a8da13599b2d_key -iv $encrypted_a8da13599b2d_iv -in codesigning.asc.enc -out codesigning.asc -d
    gpg --fast-import .travis/codesigning.asc
fi
