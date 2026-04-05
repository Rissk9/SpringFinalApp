var app = angular.module('fintechApp', ['ngRoute']);

app.config(function ($routeProvider, $locationProvider) {
    $locationProvider.html5Mode(true);
    $routeProvider
        .when("/", {
            templateUrl: "landing.html",
            controller: "MainController"
        })
        .when("/user", {
            templateUrl: "user.html",
            controller: "UserController"
        })
        .when("/admin", {
            templateUrl: "admin.html",
            controller: "AdminController"
        })
        .otherwise({
            redirectTo: '/'
        });
});

app.controller('MainController', function ($scope, $location) {
    $scope.goToUser = function () {
        $location.path('/user');
    };
    $scope.goToAdmin = function () {
        window.location.href = '/admin';
    };
});

app.controller('UserController', function ($scope, $http) {

    // View Management
    $scope.activeTab = 'unified'; // Default view
    $scope.setTab = function (tab) {
        $scope.activeTab = tab;
    };

    // --- PART 1: UNIFIED CUSTOMER PROFILE ---

    $scope.resetUnifiedForm = function () {
        $scope.customer = {
            custFullname: '',
            custGender: '',
            custDate: '',
            custPrefLanguage: '',
            custStatus: '',
            custCountry: '',
            classificationId: null,
            identification: {
                custIdentificationtype: '',
                custIdentificationItem: '',
                effectiveDate: ''
            },
            customerAddresses: [],
            customerContactInformations: [],
            customerProofofIds: [],
            customerNames: []
        };
    };

    $scope.resetUnifiedForm();

    // Helper functions for Unified Form
    $scope.addAddress = function () { $scope.customer.customerAddresses.push({ addressType: '', addressValue: '', effectiveDate: '' }); };
    $scope.removeAddress = function (index) { $scope.customer.customerAddresses.splice(index, 1); };

    $scope.addContactInfo = function () { $scope.customer.customerContactInformations.push({ customerContactType: '', customerContactValue: '', effectiveDate: '', startDate: '', endDate: '' }); };
    $scope.removeContactInfo = function (index) { $scope.customer.customerContactInformations.splice(index, 1); };

    $scope.addProofOfId = function () { $scope.customer.customerProofofIds.push({ proofofIdType: '', proofofIdValue: '', effectivDate: '', startDate: '', endDate: '' }); };
    $scope.removeProofOfId = function (index) { $scope.customer.customerProofofIds.splice(index, 1); };

    $scope.addCustomerName = function () { $scope.customer.customerNames.push({ customerNameType: '', customerNameValue: '', effectiveDate: '' }); };
    $scope.removeCustomerName = function (index) { $scope.customer.customerNames.splice(index, 1); };

    $scope.submitUnifiedForm = function () {
        console.log("Submitting Unified Profile:", $scope.customer);
        $http.post('/api/customers', $scope.customer)
            .then(res => { alert('Unified Profile added!'); $scope.resetUnifiedForm(); },
                err => { console.error("Unified Error:", err); alert('Failed to add unified profile (Check if IDs exist)'); });
    };

    $scope.submitForm = $scope.submitUnifiedForm;
    $scope.resetForm = $scope.resetUnifiedForm;


    // --- PART 2: INDIVIDUAL ENTITY ENTRIES ---

    // Initializers for individual forms
    $scope.initIndividualForms = function () {
        $scope.classification = { customerClassificationType: '', customerClassificationValue: '', effectiveDate: '' };
        $scope.address = { custId: null, customerClassificationId: null, addressType: '', addressValue: '', effectiveDate: '' };
        $scope.proof = { custId: null, proofofIdType: '', proofofIdValue: '', effectivDate: '', startDate: '', endDate: '' };
        $scope.nameEntry = { customerNameType: '', customerNameValue: '', effectiveDate: '', classificationId: null, custId: null };
        $scope.individualIdent = { custId: null, custIdentificationtype: '', custIdentificationItem: '', effectiveDate: '' };
        $scope.contact = { custId: null, customerContactType: '', customerContactValue: '', effectiveDate: '', startDate: '', endDate: '' };
    };
    $scope.initIndividualForms();

    // 1. Customer Classification
    $scope.submitClassification = function () {
        $http.post('/api/classification', $scope.classification)
            .then(res => { alert('Classification added!'); $scope.classification = {}; },
                err => { console.error(err); alert('Failed to add classification'); });
    };

    // 2. Customer Address
    $scope.submitAddress = function () {
        $http.post('/api/address', $scope.address)
            .then(res => { alert('Address added!'); $scope.address = {}; },
                err => { console.error(err); alert('Failed to add address'); });
    };

    // 3. Customer Proof of ID
    $scope.submitProof = function () {
        $http.post('/api/proof', $scope.proof)
            .then(res => { alert('Proof of ID added!'); $scope.proof = {}; },
                err => { console.error(err); alert('Failed to add proof'); });
    };

    // 4. Customer Names
    $scope.submitName = function () {
        $http.post('/api/customernames', $scope.nameEntry)
            .then(res => { alert('Name added!'); $scope.nameEntry = {}; },
                err => { console.error(err); alert('Failed to add name'); });
    };

    // 5. Customer Identification (Renamed to individualIdent for safety)
    $scope.submitIdent = function () {
        console.log("Submitting Identification:", $scope.individualIdent);
        $http.post('/api/identification', $scope.individualIdent)
            .then(res => { alert('Identification added!'); $scope.individualIdent = { custId: null, custIdentificationtype: '', custIdentificationItem: '', effectiveDate: '' }; },
                err => { console.error("Identification Error:", err); alert('Failed to add identification. Does the Customer ID exist or already have an ID?'); });
    };

    // 6. Customer Contact Info
    $scope.submitContact = function () {
        $http.post('/api/contactinfo', $scope.contact)
            .then(res => { alert('Contact info added!'); $scope.contact = {}; },
                err => { console.error(err); alert('Failed to add contact info'); });
    };

    // --- PART 3: DELETE CENTER LOGIC ---
    $scope.delIds = {};

    $scope.quickDelete = function(type, id) {
        if (!id) {
            alert('Please provide a valid ID for ' + type);
            return;
        }

        if (!confirm(`Are you sure you want to permanently delete ${type.toUpperCase()} record with ID: ${id}?`)) {
            return;
        }

        const url = `/api/${type}/${id}`;
        $http.delete(url)
            .then(res => {
                alert('Record ' + id + ' deleted successfully!');
                $scope.delIds[type] = null;
            })
            .catch(err => {
                console.error("Delete Error:", err);
                let errorMsg = 'Unknown technical error';
                
                if (err.data) {
                    if (typeof err.data === 'string') {
                        errorMsg = err.data;
                    } else if (err.data.message) {
                        errorMsg = err.data.message;
                    } else if (err.data.error) {
                        errorMsg = err.data.error;
                    }
                }
                
                alert(`Failed to delete ${type} record ${id}: ${errorMsg}`);
            });
    };

});

app.controller('AdminController', function ($scope, $http) {
    $scope.message = "Admin Side - Work in Progress (Individual entries moved to User Side)";

    $scope.uploadExcel = function() {
        var fileInput = document.getElementById('excelFile');
        if (!fileInput.files.length) {
            alert("Please select an Excel file first.");
            return;
        }

        var file = fileInput.files[0];
        var formData = new FormData();
        formData.append('file', file);

        $http.post('/api/customers/upload', formData, {
            transformRequest: angular.identity,
            headers: {'Content-Type': undefined}
        }).then(function(res) {
            alert(res.data || 'Bulk upload successful!');
            fileInput.value = ''; // Clear file selection
        }).catch(function(err) {
            console.error("Bulk Upload Error:", err);
            
            // Handle HTTP 207 Multi-Status (Some failed lines)
            if (err.status === 207 && err.data && err.data.length > 0) {
                alert("Upload finished with errors:\n\n" + 
                      err.data.slice(0, 5).join("\n") + 
                      (err.data.length > 5 ? "\n...and " + (err.data.length - 5) + " more" : ""));
            } else {
                alert("Failed to upload the file. Please trace the error in the console.");
            }
            fileInput.value = '';
        });
    };
});
