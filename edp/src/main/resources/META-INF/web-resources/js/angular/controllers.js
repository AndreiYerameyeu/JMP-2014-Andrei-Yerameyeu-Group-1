'use strict';

/* Controllers */

angular.module('myApp.controllers', []).
  controller('MyCtrl1', function ($scope, $modal, $filter, services) {
	  $scope.filterIt = function() {
		    return $filter('orderBy')($scope.list2, 'title');
		  };

		  $scope.days = [ { 'date': '01/01/2015'},
		                  { 'date': '02/01/2015'},
		                  { 'date': '03/01/2015'},
		                  { 'date': '04/01/2015'},
		                  { 'date': '05/01/2015'},
		                  { 'date': '06/01/2015'},
		                  { 'date': '07/01/2015'},
		                  { 'date': '08/01/2015'},
		                  { 'date': '09/01/2015'},
		                  { 'date': '11/01/2015'},
		              ];
		  $scope.tasks = [
		    { 'title': 'Item 3', 'drag': true },
		    { 'title': 'Item 2', 'drag': true },
		    { 'title': 'Item 1', 'drag': true },
		    { 'title': 'Item 4', 'drag': true }
		  ];
		  
		  $scope.employees = [
		          		    { 'fullname': 'Yauheni A', 'employeeDays': [] },
		        		    { 'fullname': 'Yauheni B', 'employeeDays': [] },
		        		    { 'fullname': 'Yauheni C', 'employeeDays': [] },
		        		    { 'fullname': 'Yauheni D', 'employeeDays': [] }
		        		  ];
		  

		  
		  angular.forEach($scope.days, function(val, key) {
			   for( var i in  $scope.employees){  $scope.employees[i].employeeDays.push({})
				   };
			  });
		  
  })
  .controller('MyCtrl2', [function() {
	  debugger;
  }])
  .controller('taskCtrl', function ($scope, $modal, $filter, services) {
    $scope.task = {};
    services.get('tasks').then(function(data){
        $scope.tasks = data.data;
    });
    $scope.changeTaskStatus = function(task){
        task.status = (task.status=="Active" ? "Inactive" : "Active");
        services.put("tasks/"+task.id,task);
    };
    
    $scope.changeTaskType = function(task){
        task.status = (task.type=="Dev" ? "Dev" : "Test");
        services.put("tasks/"+task.id,task);
    };
    $scope.deleteTask = function(task){
        if(confirm("Are you sure to remove the task")){
        	services.delete("tasks/"+task.id).then(function(result){
                $scope.tasks = _.without($scope.tasks, _.findWhere($scope.tasks, {id:task.id}));
            });
        }
    };
    $scope.open = function (p,size) {
        var modalInstance = $modal.open({
          templateUrl: 'pages/taskEdit.html',
          controller: 'taskEditCtrl',
          size: size,
          resolve: {
            item: function () {
              return p;
            }
          }
        });
        modalInstance.result.then(function(selectedObject) {
            if(selectedObject.save == "insert"){
                $scope.tasks.push(selectedObject);
                $scope.tasks = $filter('orderBy')($scope.tasks, 'id', 'reverse');
            }else if(selectedObject.save == "update"){
                p.description = selectedObject.description;
                p.hoursEstimated = selectedObject.hoursEstimated;
                p.type = selectedObject.type;
              
            }
        });
    };
    
 $scope.columns = [
                    {text:"ID",predicate:"id",sortable:true,dataType:"number"},
                    {text:"Name",predicate:"name",sortable:true},
                    {text:"Hours",predicate:"hours",sortable:true, dataType:"number"},
                    {text:"Type",predicate:"type",sortable:true},
                    {text:"Description",predicate:"description",sortable:true},
                    {text:"Status",predicate:"status",sortable:true},
                    {text:"Action",predicate:"",sortable:false}
                ];})
	.controller('taskEditCtrl', function ($scope, $modalInstance, item, services) {

  $scope.task = angular.copy(item);
        
        $scope.cancel = function () {
            $modalInstance.dismiss('Close');
        };
        $scope.title = (item.id > 0) ? 'Edit task' : 'Add task';
        $scope.buttonText = (item.id > 0) ? 'Update task' : 'Add New task';

        var original = item;
        $scope.isClean = function() {
            return angular.equals(original, $scope.task);
        }
        $scope.saveTask = function (task) {
            task.uid = $scope.uid;
            if(task.id > 0){
            	services.put('tasks/'+task.id, task).then(function (result) {
                    if(result.status != 'error'){
                        var x = angular.copy(task);
                        x.save = 'update';
                        $modalInstance.close(x);
                    }else{
                        console.log(result);
                    }
                });
            }else{
                task.status = 'Active';
                services.post('tasks', task).then(function (result) {
                    if(result.status != 'error'){
                        var x = angular.copy(result.data);
                        x.save = 'insert';
                        $modalInstance.close(x);
                    }else{
                        console.log(result);
                    }
                });
            }
        };
});