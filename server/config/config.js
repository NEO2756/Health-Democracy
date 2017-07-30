'use strict'

let path = require('path');

let platformConfig = {

    log: {
        level: 'debug',
        path: __dirname + '/../logs/app.log'
    },

    app: {
        port: 10001,
        models_path: __dirname + '/../models',
        serverpath: 'http://127.0.0.1',
        privateKey: 'helloere', // temp hack,
        password: 'Gemini@123',
        smsAuthkey: '88654AbtSznZEDvB55a757f2',
        smsSender: 'MEDREM'
    },

    mongo: {
        seed: true,
        useSeedData: true,
        url: process.env.MONGOHQ_URL || process.env.MONGOLAB_URI || 'mongodb://127.0.0.1:27017/healthchain'
    }

};

module.exports = platformConfig;
