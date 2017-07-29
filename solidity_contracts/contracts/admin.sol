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
        
    }

    contract Pharma
      {
 
       string public pname;
 
       function Pharma(string pharma_name)
       {
         pname = pharma_name;
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

  // How to generate ABI https://ethereum.github.io/browser-solidity/
  // [{"constant":true,"inputs":[],"name":"getName","outputs":[{"name":"name","type":"bytes32"}],"payable":false,"type":"function"},{"constant":true,"inputs":[],"name":"getaddress","outputs":[{"name":"add","type":"bytes32"}],"payable":false,"type":"function"},{"constant":true,"inputs":[],"name":"getbloodgrp","outputs":[{"name":"blood_grp","type":"bytes32"}],"payable":false,"type":"function"},{"constant":true,"inputs":[],"name":"p","outputs":[{"name":"name","type":"bytes32"},{"name":"p_address","type":"bytes32"},{"name":"dob","type":"uint256"},{"name":"blood_grp","type":"bytes32"},{"name":"phnum","type":"bytes32"}],"payable":false,"type":"function"},{"constant":false,"inputs":[{"name":"bpm","type":"uint256"},{"name":"bp","type":"uint256"},{"name":"spo2","type":"bytes32"}],"name":"addHealthData","outputs":[],"payable":false,"type":"function"},{"constant":true,"inputs":[],"name":"getdob","outputs":[{"name":"dob","type":"uint256"}],"payable":false,"type":"function"},{"constant":true,"inputs":[],"name":"getphnum","outputs":[{"name":"phnum","type":"bytes32"}],"payable":false,"type":"function"},{"inputs":[{"name":"name","type":"bytes32"},{"name":"p_address","type":"bytes32"},{"name":"dob","type":"uint256"},{"name":"blood_grp","type":"bytes32"},{"name":"phnum","type":"bytes32"}],"payable":false,"type":"constructor"},{"anonymous":false,"inputs":[{"indexed":false,"name":"bpm","type":"uint256"},{"indexed":false,"name":"bp","type":"uint256"},{"indexed":false,"name":"spo2","type":"bytes32"}],"name":"logEvent","type":"event"}]
