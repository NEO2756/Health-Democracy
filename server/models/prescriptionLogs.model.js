'use strict'

var mongoose = require('mongoose');
var Schema = mongoose.Schema;


var PrescriptioLogs = new Schema({	
	patientId : { type : String},
	prescription: [{
		medicineName: String,
		timesADay : Number,
		fromDate: Date,
		tillDate: Date,
		doctorId: String,
		clockOne: String,
		clockTwo: String,
		clockThree: String,
		clockFour: String
	}]
})

module.exports = mongoose.model('PrescriptioLogs', PrescriptioLogs);