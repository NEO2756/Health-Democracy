'use strict'

var mongoose = require('mongoose');
var Schema = mongoose.Schema;


var MedicineLogs = new Schema({
	patientId :{type:String, required:true},
    bpm :{type:String},
    bp :{type:String},
    spo2 :{type:String}
})

module.exports = mongoose.model('MedicineLogs', MedicineLogs);