pragma solidity ^0.4.11;


    contract Patient
    {
        struct patient
        {
            string name;
            string p_address;
            uint dob;
            string blood_grp;
            string phnum;
        }
        
        struct medicineStatus{
            string medicineName;
            string time;
            bool status;
        }

        struct prescription{
          string medicineName;
          uint timesADay;
          string fromDate;
          string tillDate;
          string doctorId;
        }
        
        medicineStatus[] public medStatus;
        prescription[] public prescriptionArr;
        
        
        patient public p;
        function Patient(string name, string p_address, uint dob, string blood_grp, string phnum)
        {
          p.name = name;
          p.p_address = p_address;
          p.dob = dob;
          p.blood_grp = blood_grp;
          p.phnum=phnum;
        }

        function getName() constant returns (string name)
        {
            return p.name;
        }

        function getaddress() constant returns (string add)
        {
            return p.p_address;
        }

        function getdob() constant returns (uint dob)
        {
            return p.dob;
        }

        function getbloodgrp() constant returns (string blood_grp)
        {
            return p.blood_grp;
        }

        function getphnum() constant returns (string phnum)
        {
            return p.phnum;
        }

        function addHealthData(uint bpm, uint bp, string spo2)
        {
         logEvent(bpm, bp, spo2);
        }
        event logEvent(uint bpm, uint bp, string spo2);
        
        function storeMedicineStatus(string medicineName, string time,  bool status){
            medicineStatus memory  m;
            m.medicineName = medicineName;
            m.time = time;
            m.status = status;
            medStatus.push(m);
        }

        function storePrescription (string medicineName, uint timesADay, string fromDate, string tillDate, string doctorId ){
          prescription memory p;
          p.medicineName = medicineName;
          p.timesADay = timesADay;
          p.fromDate = fromDate;
          p.tillDate = tillDate;
          p.doctorId = doctorId;
          prescriptionArr.push(p);
        }
        
        /*function getMedicineStatusLogs() constant returns(medStatus){
            return medStatus;
        }*/

        function getTotalPrescriptionsLength() constant returns (uint length1){
            return prescriptionArr.length;
        }

        function getPrescription(uint index1) public constant returns(string medicineName, uint timesADay, string fromDate, string tillDate, string doctorId){
        return (prescriptionArr[index1].medicineName,
                prescriptionArr[index1].timesADay,
                prescriptionArr[index1].fromDate,
                prescriptionArr[index1].tillDate,
                prescriptionArr[index1].medicineName);
      }
    
    }

    contract Pharma
      {
 
       string public pname;
       event customerRequest(address customer, string medName, uint quantity);
 
       function Pharma(string pharma_name)
       {
         pname = pharma_name;
       }

      function getRequest(address customer, string medName, uint quantity) public payable returns (bool a)
      {
        customerRequest(customer, medName, quantity);
        return true;
      }
       
      }

    contract admin
    {
        mapping (address => bool) public patients;
        address public Admin;
        address[] public patientsAddr;
        address[] public pharmaAddr;
        mapping (address =>mapping (uint => string)) public records;

        function admin()
        {
            Admin = msg.sender;
        }

        modifier adminOnly
        {
            if (Admin != msg.sender) throw;
            _;
        }

     function registerPatient (string name, string p_address, uint dob, string blood_grp, string phnum) adminOnly
        {
          address patientContract = new Patient(name, p_address, dob, blood_grp, phnum);
          patientsAddr.push(patientContract);
          patients[patientContract] = true;
          log(patientContract, "New Patient Registered");
        }

        function registerPharma(string name) adminOnly
        {
          address pharmaContract = new Pharma(name);
          pharmaAddr.push(pharmaContract);
          log(pharmaContract, "New Pharma Registered");
        }

        function getPharmaContract() constant returns(address addr)
        {
          return pharmaAddr[0];
        }

        function  getFirstPatietAddress() constant returns (address addr){
            return patientsAddr[0];
        }

        function getLatestPatientAddress() constant returns (address addr){
            uint length= patientsAddr.length;
            return patientsAddr[length-1];
        }

        function addRecords(address id, uint day, string data)
        {
         if (!patients[id])
         {
                 throw;
         }
         else
         {
                records[id][day] = data;
                log(id, data);
         }
        }

        event log(address id, string desc);
    }