FROM maven:3.8.6-jdk-17-slim

# install Google Chrome
RUN apt-get update \
    && apt-get install -y wget gnupg2 \
    && wget -qO - https://dl-ssl.google.com/linux/linux_signing_key.pub \
         | apt-key add - \
    && echo "deb [arch=amd64] http://dl.google.com/linux/chrome/deb/ stable main" \
         > /etc/apt/sources.list.d/google-chrome.list \
    && apt-get update \
    && apt-get install -y google-chrome-stable \
    && rm -rf /var/lib/apt/lists/*

# ChromeDriver: get the version matching Chrome
ARG CHROME_DRIVER_VERSION=114.0.5735.90
RUN wget -O /tmp/chromedriver.zip \
      https://chromedriver.storage.googleapis.com/$CHROME_DRIVER_VERSION/chromedriver_linux64.zip \
    && unzip /tmp/chromedriver.zip -d /usr/local/bin/ \
    && rm /tmp/chromedriver.zip

WORKDIR /workspace

