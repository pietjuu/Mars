- [Shippert Client](#shippert-client)
  * [Public urls](#public-urls)
  * [Getting Started](#getting-started)
  * [Building and Deploying](#building-and-deploying)
  * [Project Structure](#project-structure)
  * [Known Bugs](#known-bugs)
  * [Unfinished Requirements](#unfinished-requirements)
  + [Additional Resources](#additional-resources)
  + [Instructions for testing locally](#instructions-for-testing-locally)
  + [Instruction for testing the web client locally with a deployed mars-server](#instruction-for-testing-the-web-client-locally-with-a-deployed-mars-server)
  + [Instructions for local quality checks](#instructions-for-local-quality-checks)


# Shippert Client
Welcome to our web application!  

This is the client-side of the Shippert Digital Platform application.  
This application is built using the VueJS cli, a command line interface tool for creating Vue.js projects.

## Public urls  
* Web project: https://project-ii.ti.howest.be/mars-05/
* Sonar reports: https://sonar.ti.howest.be/dashboard?id=2022.project-ii%3Amars-client-05



## Getting Started
To get started with the application, you will need to have Node.js and the Vue cli installed on your machine. 
If you don't have these already, you can install them by following the instructions on the Vue cli documentation page: https://cli.vuejs.org/

Once you have the prerequisites installed, clone this repository and navigate to the root directory of the project:
```bash
git clone git@git.ti.howest.be:TI/2022-2023/s3/analysis-and-development-project/projects/group-05/client.git
cd client
```
Next, install the dependencies for the project:
```bash
npm install
```

Finally, start the development server by running:
```bash
npm run serve
```

This will start the development server and open the application in your default browser. Any changes you make to the code will automatically be reflected in the browser.

## Building and Deploying
To build the application for production, run:
```bash
npm run build
```
This will create a production-ready build of the application in the `dist` directory. You can then deploy this build to your chosen hosting platform.

Finally, to deploy the build, simply push the changes to the remote repository. 
Once the changes are merged with the main branch and all the pipelines were successful, the `dist` directory will be deployed on the webserver.

To automate this process of building the application and pushing the changes, we build a script **deploy.ps1**, run the following to execute the script:
```bash
./bin/deploy.ps1
```

## Project Structure
The project is structured as follows:
- `src`: This directory contains the source code for the application.  
- `public`: This directory contains any static assets that you want to be available to the application.  
- `node_modules`: This directory contains the dependencies for the project, installed by npm.  
- `bin`: This directory contains executable scripts

## Known Bugs  
- Unable to delete blacklist items since server implemented the H2 Database
- The data processing for the statistics isn't completely correct. Items sent or received today are shown on the line chart at a earlier date.
- Not all destination IDs have a transporter name, so that is why some IDs are displayed instead of transporter names.
- In some rare a radio list might show that there is a radio selected but in reality there is none selected. We have no idea how to simulate this bug.

## Unfinished Requirements  
- Websocket. Instead we temporarily use polling.
- Fullscreen API for destinations map
- Notification API

## Additional Resources
- VueJS documentation: https://vuejs.org/guide/  
- Vue cli documentation: https://cli.vuejs.org/  
- Wireframes: [link to wireframes](https://www.figma.com/proto/0H1qaMAdJlrgHPyx3rzOwH/SHIPPERT-DIGITAL-PLATFORM?page-id=0%3A1&node-id=43%3A694&viewport=736%2C2465%2C1.1&scaling=scale-down&starting-point-node-id=6%3A351)  

## Instructions for testing locally
* Run the mars-server with gradle run (through your IDE)
* Open the mars-client in phpstorm/webstorm
  * Navigate to the index.html
  * Click on a browser icon at the top right of your IDE to host the mars-client.
  
## Instruction for testing the web client locally with a deployed mars-server
* Open the mars-client in phpstorm
  * Copy the following settings to **config.json** (make sure to replace the XX)
```json
      {
        "host": "https://project-ii.ti.howest.be",
        "folder": "",
        "group": "mars-XX"
      }
```
  * Navigate to the index.html
  * Click on a browser icon at the top right of your IDE to host the mars-client.
  * Make sure to undo the settings once you are done testing the remote server!

## Instructions for local quality checks
You can run the validators for html, CSS and JS rules locally. 

Make sure **npm** is installed.

There is no need to push to the server to check if you are compliant with our rules. 

In the interest of sparing the server, please result to local testing as often as possible. 

If everyone pushes to test, the remote will not last. 

Open a terminal in your IDE
  - Make sure you are in the root folder of the client project.
  - Execute `npm install` this step is only needed once.
  - Execute `npm run validate-local` for linux/mac users.
  - Execute `npm run validate-local-win` for Windows users. 
  - If there are errors, the program execution will halt and show the first error
  - If there are no errors, a report file will be generated in the `.scannerworks/` directory. 
    - You will find the link to the sonar report in this file 

Hint:

If you want to skip ci remotely, include `[ci skip]` in your commit message. 

This is convenient for when you want to quickly add a certain commit, but do not wish to trigger the whole CI sequence. 

