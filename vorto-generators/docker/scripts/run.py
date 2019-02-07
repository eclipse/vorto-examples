from os import getenv
from subprocess import check_call
import json, sys, yaml
from pathlib import Path
import argparse

def proxy_url_to_java(proxy_url):
    user = None
    password = None
    if 'http://' in proxy_url:
        proxy_url = proxy_url.replace('http://', '')
    elif 'https://' in proxy_url:
        proxy_url = proxy_url.replace('https://', '')
    if '@' in proxy_url:
        user_and_password, host_and_port = proxy_url.split("@")
        user, password = user_and_password.split(":")
    else:
        host_and_port = proxy_url
    host, port = host_and_port.split(":")
    return host, port, user, password


def main():
    # Read in arguments from cli

    config_yaml_file = Path("./config/application.yml")
    java_args = ["java"]
    args = {}
    profile = getenv("PROFILE", "local")
    args.update({"spring.profiles.active": profile})
    spring_application = {}

    # generell options
    if config_yaml_file.is_file():
        with open("./config/application.yml") as yaml_file:
            yaml_data = yaml.safe_load(yaml_file.read())
        spring_application.update(yaml_data)
    else:
        #Things that can be defaulted
        args.update({"server.port": getenv("VORTO_PORT", 8080)})
        args.update({"server.contextPath": getenv("CONTEXT_PATH", "/")})
        args.update({"server.config.generatorUser": getenv("GENERATOR_USER", "vorto_generators")})
        if getenv("GENERATOR_PASSWORD"):
            args.update({"server.config.generatorPassword": getenv("GENERATOR_PASSWORD")})
        else:
            print("No Generator Password set, generators won't work")

    # generator settings
    if getenv("3RD_PARTY_SERVICE_URL"):
        args.update({"server.serviceUrl": getenv("3RD_PARTY_SERVICE_URL")})
    else:
        print("please pass the 3RD_PARTY_SERVICE_URL to the repository")
    if getenv("VORTO_URL"):
        args.update({"vorto.serverUrl": getenv("VORTO_URL")})
    else:
        print("please pass the VORTO_URL to the repository")

    #run java
    args.update({"spring.application.json": json.dumps(spring_application)})
    for key, item in args.items():
        java_args.append("-D{}={}".format(key,item))
    java_args.append("-jar")
    java_args.append("generators.jar")
    print(java_args)
    check_call(java_args)

main()
