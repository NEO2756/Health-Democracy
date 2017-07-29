## Prerequisites

* Install [node](https://nodejs.org/en/)
* Install [MongoDB](https://docs.mongodb.org/manual/installation/)
* Install [PM2](https://github.com/Unitech/pm2)
* Install [truffle](http://truffleframework.com/docs/getting_started/installation)
* Install [ethereum](https://github.com/ethereumjs/testrpc)

## Environment Variables
```
NODE_ENV           (Environment name, possible values are 'production', 'development')
PORT               (API running port)
MONGOLAB_URI       (MongoDB URL)
```


## Development Prerequisites

* Install `yo`, `grunt-cli`, `bower`, and `generator-angular-fullstack`:

```
npm install -g node yo grunt-cli bower generator-angular-fullstack
npm -g install truffle 
npm install -g ethereumjs-testrpc
```

## Project Structure

    ├── api
    │   ├── api.controller.js
    │   ├── index.js
    │   └── services.js
    ├── config
    │   └── index.js
    ├── hello.yml
    ├── models
    │   ├── blockchainMedicineLogs.model.js
    │   ├── medicineLogs.model.js
    │   ├── patients.model.js
    │   ├── prescriptionLogs.model.js
    │   └── schedules.model.js
    ├── npm-debug.log
    ├── package.json
    ├── routes.js
    ├── server.js
    └── services
        └── patient.service.js




## Configuration Files

To update the server configurations: -

- App Port Setting,
- MongoDB Connection


## Installation

Clone Repository:

* Login with your credentials on https://github.com/3PillarGlobal/Health-Democracy.git
* Click on "Clone" copy the repository clone path
* Run it on you machine terminal

```
Clone repository as said above
cd Health-Democracy

cd server
npm install
```

## Setup Private blockchain

```
testrpc
cd solidity_contracts
truffle compile
truffle migrate
```

Smart Contract directory generated

      ├── build
      │   └── contracts
      │       ├── admin.json
      │       ├── ConvertLib.json
      │       ├── MetaCoin.json
      │       ├── Migrations.json
      │       ├── Patient.json
      │       └── Pharma.json
      ├── contracts
      │   ├── admin.sol
      │   ├── ConvertLib.sol
      │   ├── MetaCoin.sol
      │   └── Migrations.sol
      ├── migrations
      │   ├── 1_initial_migration.js
      │   └── 2_deploy_contracts.js
      ├── npm-debug.log
      ├── test
      │   ├── metacoin.js
      │   └── TestMetacoin.sol
      └── truffle.js


## Setup private blockchain on AWS

This will consider node packages are install as required for server (i.e. required package.json file found inside server folder)

```
  pm2 start testrpc                            # starts testrpc on AWS
  pm2 start server.js                          # starts server on AWS
```
