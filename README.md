# Planets
[![Build Status](http://build.andrey96.ru/job/Planets/badge/icon)](http://build.andrey96.ru/job/Planets/)

## Editing

1. Clone this repo
2. Run 'gradlew eclipse'
3. Run eclipse and import repository folder as an existing project
4. To run project in eclipse you should add -Djava.library.path=build/natives/windows
(or .../linux for linux) to JVM arguments in run configuration

## Building

1. Clone this repo
2. Run 'gradlew buildDistr'
3. You will find application in build\distributions folder

## Running

1. Build or download latest build from [build service](http://build.andrey96.ru/job/Planets/)
2. Unpack correct natives folder from Planets-build..-natives.zip (windows folder is for windows, etc)
3. Run 'java -Djava.library.path=windows -jar Planets-build...jar' (or use linux/osx instead of windows)

## Run paramethers

Into the end of run command line you can add some paramethers like '-fpsLimit 1000'

| Paramether | Value type | Description |
|---|---|---|
| -width | Integer | Starting window width |
| -height | Integer | Starting window height |
| -samples | Byte (from 0 to 8) | Multisampling |
| -details | Short | Sphere detalization |
| -fpsLimit | Integer | FPS limit, if not specified VSync is used |
| -resDir | Integer | Resource directory (if you unpacked textures from jar file) |
