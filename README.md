PSTAnalyzerr
=======
SIS2014/2015@FOI

TASK:
Create a software for reading emails from PST file, analyses it's data (from, to, cc, bcc, subject and content). 
There is requirement for full-text search in emails. 
UI allows search for each email by it's content, from, to, subject... Show emails for given period of time and count them for each person. Main goal is to graphically show connection between senders and receivers inside PST file.
Project is still in the development phase.

How to use application:
--------------
1. After application has started, choose PST file.
2. Press "Launch" button
3. After PST file is analysed (can take up to a few minutes depending on file size), open web browser with address: http://localhost:4567/ 


Used technologies:
--------------
- Intellij IDEA 14.0.1 (IDE)
- Maven 
- Elasticsearch 1.4.1
- Java Lib-Pst 0.8.1
- Spark Java 2.1
- Jest 0.1.3
- Kibana 3.1.2
- Other dependencies defined in pom.xml file

**JavaDoc:** http://arka.foi.hr/~mvukovic2/PSTAnalyzerr/JavaDocs/index.html

**Project Wiki (In Croatian):** http://security.foi.hr/wiki/index.php/Analiza_PST_datoteka

Useful links:
* ibm elasticsearch+java tutorial:
  http://www.ibm.com/developerworks/library/j-javadev2-24/
* java-libpst:
  https://github.com/rjohnsondev/java-libpst
* java 3rd mail api list:
  http://www.oracle.com/technetwork/java/javamail/third-party-136965.html
* Elasticsearch book:
  https://www.packtpub.com/big-data-and-business-intelligence/elasticsearch-cookbook
* http://www.socallinuxexpo.org/scale12x-supporting/default/files/presentations/Scale12x%20-%20Intro%20to%20Elasticsearch%20%28Kluge%29.pdf


Licensing
----------------
The MIT License (MIT)

Copyright (c) 2015 Viktor Lazar, Martina Šestak, Goran Vodomin, Matej Vuković

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in
all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
THE SOFTWARE.
