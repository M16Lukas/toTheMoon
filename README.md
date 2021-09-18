# 🌕 toTheMoon
 
<!-- TABLE OF CONTENTS -->
<details open="open">
  <summary>Table of Contents</summary>
  <ol>
    <li>
      <a href="#about-the-project">About The Project</a>
      <ul>
        <li><a href="#built-with">Built With</a></li>
        <li><a href="#api">API</a></li>
        <li><a href="#project-structure">Project Structure</a></li>
      </ul>
    </li>
    <li>
      <a href="#getting-started">Getting Started</a>
      <ul>
        <li><a href="#installation">Installation</a></li>
      </ul>
    </li>
    <li>
      <a href="#usage">Usage</a>
      <ul>
        <li><a href="#summary-page">Summary Page(詳細情報)</a></li>
        <li><a href="#chart-page">Chart Page(チャート)</a></li>
        <li><a href="#conversations-page">Conversations Page(掲示板)</a></li>
        <li><a href="#historical-data-page">Historical Data Page(時系列)</a></li>
      </ul>
    </li>
    <li><a href="#contact">Contact</a></li>
    <li><a href="#acknowledgements">Acknowledgements</a></li>
  </ol>
</details>
 
<!-- ABOUT THE PROJECT -->
## About The Project

コロナ禍、住宅価格の急上昇などの影響でビットコインや株式投資に関する人々の関心が高まっています。

その中でもアメリカ株に投資をする投資家が増えていることは注目すべきの現象だと考えています。

しかし、株取引初心者たちは複雑な株式用語や厄介な情報収集などなど壁にぶつかって株式投資入門に苦労しています。

<b>toTheMoon</b>は知りたいアメリカ株式を検索すると株式の基本情報やニュース、チャート、他人のコメント、時系列を一目でわかるように提供して株取引をサポートするサービスです。

### Built With

* [Spring Tools 4 for Eclipse (4.3.6.RELEASE)](https://spring.io/tools)
* [MyBatis 3.4.6](https://github.com/mybatis/mybatis-3/releases/tag/mybatis-3.4.6)
* [JAVA 8](https://www.oracle.com/java/technologies/javase/javase-jdk8-downloads.html)
* [Oracle 11g](https://edelivery.oracle.com/osdc/faces/SoftwareDelivery)
* [JQuery](https://jquery.com)
* JavaScript
* HTML5/CSS3


### API
Status | Name | Site 
---- | ---- | ---- 
Required | Yahoo Finance API - Finance Quotes API for Yahoo Finance (Java) | [GitHub](https://github.com/sstrickx/yahoofinance-api) /  [Website](https://financequotes-api.com/)
Required | Google API Client Library for Java | [Website](https://developers.google.com/api-client-library/java)

<!-- Project Structure -->
### Project Structure
![image](https://user-images.githubusercontent.com/46748131/130345759-74995b67-5afd-499d-a53d-71e2404c452b.jpg)


<!-- Getting Started -->
## Getting Started

### Installation

#### How to deploy to Apache Servers in a Windows Environment

1. Download [WAR file & sql file](https://github.com/M16Lukas/toTheMoon/releases)

2. Copy and paste the sql file code into Oracle

3.  Move the WAR file into the  **[tomcat installation path] \ webapps**

4.  Modify  **[tomcat installation path] \ conf \ server.xml**

+ port (8080 -> 8888) : Because, port 8080 in use by ORACLE 11g

```
 <Connector port="8888" protocol="HTTP/1.1"
               connectionTimeout="20000"
               redirectPort="8443" />
```

+ localhost path (add ```<Context />```)

```
<Host name="localhost"  appBase="webapps"
            unpackWARs="true" autoDeploy="true">
            <Context path="/" docBase="toTheMoon" reloadable="true" />
```
5.  Run CMD as Administrator

6.  Go to the __[tomcat installation path] \ bin__ 

7.  Run ```startup.bat```

8.  Enter into ```localhost:8888``` browser

<!-- USAGE -->
## Usage

### Summary Page

![Summary Page](https://user-images.githubusercontent.com/46748131/129910550-2f6ba23f-d050-4002-b9a0-db3c26211129.gif)
検索した株式の詳細情報、チャート(３ヶ月)、ニュースを提供するページです。

### Chart Page

![Chart page](https://user-images.githubusercontent.com/46748131/129910608-f9e7e46a-570a-4e55-8929-3e9e908c58bc.gif)
一定期間の株価をチャートで見やすく確認できるページです。

### Conversations Page

![Conversations Page Example](https://user-images.githubusercontent.com/46748131/129910684-c62a06ba-efa1-402b-9445-9224f93f4050.gif)
株式に関して意見交換する掲示板ページです。

### Historical Data Page

![Historical Data Page Example](https://user-images.githubusercontent.com/46748131/129910754-09e79be4-eb6a-4149-b453-a7d43eada18c.gif)
株式の過去データを時系列で検索、ダウンロードできるページです。


<!-- CONTECT -->
## Contact

Minho Park - ipark308@gmail.com

Project Link : [https://github.com/M16Lukas/toTheMoon](https://github.com/M16Lukas/toTheMoon)

<!-- Acknowledgements -->
## Acknowledgements
* [Google Sign-In for Websites](https://developers.google.com/identity/sign-in/web/sign-in)
* [JQuery UI](https://jqueryui.com)
* [toastr](https://github.com/CodeSeven/toastr)
* [canvasJS - JavaScript StockChart with Area Chart](https://canvasjs.com/javascript-stockcharts/area-stockchart)
* [canvasJS - JavaScript StockChart with Price & Volume](https://canvasjs.com/javascript-stockcharts/stockchart-price-volume)
* [Bootstrap themes - SB Admin 2](https://startbootstrap.com/theme/sb-admin-2) 
* [Bootstrap themes - Landing Page](https://startbootstrap.com/theme/landing-page)

