 <!DOCTYPE html>
<html>
<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.0.8/angular.min.js"></script>
<script src="resources/js/studentManager.js" ></script>
<body ng-app="myApp" ng-controller="studentController">

<div class="container">

<h3>Students</h3>
<div>
<table class="table table-striped">
  <thead><tr>
    
    <th>First Name</th>
    <th>Last Name</th>
    
  </tr></thead>
  <tbody><tr ng-repeat="student in students">
    
    <td>{{ student.firstName }}</td>
    <td>{{ student.lastName }}</td>
	
	<td>
    <div>
      <button class="btn" ng-click="showHideAddEdit('View',student.studentNumber)">
      <span class="glyphicon glyphicon-th-list"></span>&nbsp;&nbsp;View
      </button>
      </div>
    </td>
    <td>
    <div>
      <button class="btn" ng-click="showHideAddEdit('Edit',student.studentNumber)">
      <span class="glyphicon glyphicon-pencil"></span>&nbsp;&nbsp;Edit
      </button>
      </div>
    </td>
    
    <td>
    <div>
      <button class="btn" ng-click="deleteStudent(student.studentNumber)">
      <span class="glyphicon glyphicon-trash"></span>&nbsp;&nbsp;Delete
      </button>
      </div>
    </td>
  </tr></tbody>
</table>
</div>
<hr>
<div>
<button class="btn btn-success" ng-click="showHideAddEdit('New','')">
  <span class="glyphicon glyphicon-user"></span> Create New Student
</button>
</div>
<hr>

<div ng-show="showStudentDetails">
<h3 ng-show="newStudent">Create New Student:</h3>
<h3 ng-show="updateStudent">Edit Student:</h3>
<h3 ng-show="viewStudent">Student details:</h3>

<form class="form-horizontal">

<div class="form-group">
  <label class="col-sm-2 control-label">Student Number:</label>
  <div class="col-sm-10">
    <input type="text" ng-model="stdNumber" ng-disabled="!stuNumEditable" placeholder="Student Number">
  </div>
</div>
<div class="form-group">
  <label class="col-sm-2 control-label">First Name:</label>
  <div class="col-sm-10">
    <input type="text" ng-model="fName" ng-disabled="!editable"  placeholder="First Name">
  </div>
</div>
<div class="form-group">
  <label class="col-sm-2 control-label">Last Name:</label>
  <div class="col-sm-10">
    <input type="text" ng-model="lName" ng-disabled="!editable" placeholder="Last Name">
  </div>
</div>
<div class="form-group">
  <label class="col-sm-2 control-label">Preffered Name:</label>
  <div class="col-sm-10">
    <input type="text" ng-model="pfName" ng-disabled="!editable" placeholder="Preffered Name">
  </div>
</div>

<div class="form-group">
<label class="col-sm-2 control-label">Gender:</label>
<div class="col-sm-10">

    
    <div class="radio">
      <label><input type="radio" value = "F" name="gender" data-ng-model="gender" ng-disabled="!editable">Female</label>
    </div>
    <div class="radio">
      <label><input type="radio" value = "M" name="gender" data-ng-model="gender" ng-disabled="!editable" >Male</label>
    </div>
  
  </div>
  </div>
  
<div class="form-group">
<label class="col-sm-2 control-label">Disabled :</label>
<div class="col-sm-10">

    
    <div class="radio">
      <label><input type="radio" value = "true" name="disability" data-ng-model="disability" ng-disabled="!editable">Yes</label>
    </div>
    <div class="radio">
      <label><input type="radio" value = "false" name="disability" data-ng-model="disability" ng-disabled="!editable" >No</label>
    </div>
  
  </div>
  </div> 
  
<div class="form-group">
  <label class="col-sm-2 control-label">email:</label>
  <div class="col-sm-10">
    <input type="text" ng-model="email"  ng-disabled="!editable" placeholder="email">
  </div>
</div>
</form>
</div>
<hr>
<div>
<button class="btn btn-success" ng-show="newStudent"  ng-click="addStudent()">
  <span class="glyphicon glyphicon-user"></span> Save
</button>
</div>
<div>
<button class="btn btn-success" ng-show="updateStudent" ng-click="editStudent()">
  <span class="glyphicon glyphicon-user"></span> Save
</button>
</div>



</div>

</body>
</html>