var app = angular.module('myApp', []);

function studentController($scope, $http) {
	listStudentFunction( $scope, $http );
	$scope.showStudentDetails = false;
	
	 $scope.listStudents = function() {           
     	listStudentFunction( $scope, $http );
     };
     
     $scope.addStudent = function() {
     	
    	 var dataObj = {
					"studentNumber" : $scope.stdNumber,
					"preferredName" : $scope.pfName,
					"gender" : $scope.gender,
					"firstName" : $scope.fName,
					"lastName" : $scope.lName,
					"email" : $scope.email,
					"disability" : $scope.disability
					
			};		
         
			var response = $http.post('addStudent', dataObj);
			response.success(function(data, status, headers, config) {
				
				listStudentFunction($scope, $http);
			});
			response.error(function(data, status, headers, config) {
				alert( "Error details: " + JSON.stringify({data: data}));
			});
     
     };
     
     $scope.showHideAddEdit = function(action,param) { 
         $scope.showStudentDetails = true;	 
     	 if (action == 'New') {
     		    $scope.stuNumEditable = true;
				$scope.editable = true;
     		    $scope.newStudent = true;
				$scope.updateStudent = false;
				$scope.viewStudent = false;
				$scope.stdNumber = '';
     		    $scope.fName = '';
     		    $scope.lName = '';
     		    $scope.pfName = '';
     		    $scope.email ='';
				$scope.gender = 'F';
				$scope.disability = 'true';
     		   
     		    } else {
				
				
     		    $scope.edit = false;				
     		    $http({method: 'GET', url: 'studentDetails'+'/'+param}).
     	        success(function(data, status, headers, config) {
     	        	$scope.stdNumber = data.result.studentNumber;
     	            $scope.fName = data.result.firstName;
     	            $scope.lName = data.result.lastName;
     	           $scope.email  = data.result.email;
     	          $scope.pfName = data.result.preferredName;
     	         $scope.gender = data.result.gender;
     	        $scope.disability = data.result.disability;
				
				 
				 if(action == 'Edit'){
				 $scope.stuNumEditable = false;
				$scope.editable = true;
     		    $scope.newStudent = false;
				$scope.updateStudent = true;
				$scope.viewStudent = false;
				}
				 else{
				 $scope.stuNumEditable = false;
				$scope.editable = false;
     		    $scope.newStudent = false;
				$scope.updateStudent = false;
				$scope.viewStudent = true;
				 }
     	        }).
     	        error(function(data, status, headers, config) {
     	        	alert( "Error details: " + JSON.stringify({data: data}));
     	        });
     		  }
     
     };
     
     $scope.editStudent = function() {
    	 var dataObj = {
					"studentNumber" : $scope.stdNumber,
					"preferredName" : $scope.pfName,
					"gender" : $scope.gender,
					"firstName" : $scope.fName,
					"lastName" : $scope.lName,
					"email" : $scope.email,
					"disability" : $scope.disability
					
			};		
     	
     	var response = $http.post('editStudent', dataObj);
			response.success(function(data, status, headers, config) {
				
				listStudentFunction($scope, $http);
			});
			response.error(function(data, status, headers, config) {
				alert( "Error details: " + JSON.stringify({data: data}));
			});
        
     
     };
     
     $scope.deleteStudent = function(studentNumber) {           
         $http({method: 'GET', url: 'deleteStudent'+'/'+studentNumber}).
         success(function(data, status, headers, config) {
             $scope.status = data.status;
			 listStudentFunction($scope, $http);
         }).
         error(function(data, status, headers, config) {
        	 alert( "Error details: " + JSON.stringify({data: data}));
         });
     
     };
}
   

function listStudentFunction( $scope, $http )
{
	$http({method: 'GET', url: 'listStudents'}).
            success(function(data, status, headers, config) {
                $scope.students = data.result;
            }).
            error(function(data, status, headers, config) {
            	alert( "Error details: " + JSON.stringify({data: data}));
            });
}
  
