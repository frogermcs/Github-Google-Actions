# Github Google Actions

This is example Google Home/Google Assistant Actions written in Java, ready to deploy on Google AppEngine. Detailed description can be found in blog posts:

- [Hello world Google Home](http://frogermcs.github.io/Hello-world-Google-Home/)

## Installation

_Remember to update Project ID before deploying on Google AppEngine_

- Create new project in [Google Cloud Platform dashboard](https://console.cloud.google.com/home/dashboard)
- Install [Google Cloud SDK](https://cloud.google.com/sdk/docs/)
- Install [gactions](https://developers.google.com/actions/tools/gactions-cli)
- `$ ./gradlew app:appengineDeploy`
- `$ gactions preview -action_package=app/action.json -invocation_name=Github`
- Test in [Web Simulator](https://developers.google.com/actions/tools/web-simulator)


