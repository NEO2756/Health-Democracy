'use strict'


var express = require('express'),
	router = express.Router(),
	controller = require('./api.controller');
router.post('/registerPatient', controller.registerPatient);
router.post('/getPatinetDetails', controller.getPatinetDetails);
router.post('/getTransactionReceipt', controller.getTransactionReceipt);
router.post('/logData', controller.logData);
router.post('/addPrescription', controller.addPrescription);
router.post('/getPatients', controller.getPatients);
router.post('/countPatients', controller.countPatients);
//router.post('/getSchedule', controller.getSchedule);
router.post('/getPrescriptionLogs', controller.getPrescriptionLogs);
router.post('/updateMedicineTakeStatus', controller.updateMedicineTakeStatus);
router.post('/addMedicineLogs', controller.addMedicineLogs);
router.post('/addMedicineLogsToBlockchain', controller.addMedicineLogsToBlockchain);
router.post('/getPrescriptionFromBlockchain', controller.getPrescriptionFromBlockchain);
router.post('/savePrescriptionToBlockchain', controller.savePrescriptionToBlockchain);
router.post('/sendSMS', controller.sendSMS);
router.post('/requestFromPharma', controller.requestFromPharma);
router.post('/registerPharmaToBlockchain', controller.registerPharmaToBlockchain);

module.exports = router;
