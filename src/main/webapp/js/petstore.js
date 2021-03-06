var app = angular.module("petApp", []);

app.controller("PetCtrl", function($scope, $http, $location) {
	
	$scope.statuses = ['available', 'pending', 'sold'];
	$scope.columns = ['id', 'name', 'photo', 'status']
	
	/**
	 * init
	 */
	$scope.init = function() {
		
		$scope.user();
		$scope.get();
		
	}
	
	/**
	 * clear
	 */
	$scope.clear = function() {
		
		$('.error').hide();
		$('.rheader .rdelete').hide();
		$("[class*='rselect']").removeClass('rselect');
		
	}
	
	/**
	 * create
	 */
	$scope.create = function() {
		
		$scope.clear();
		
		//post
		$http({
			
			method: 'POST',
			url: 'pet',
			headers: {'Content-Type': 'application/x-www-form-urlencoded'},
			data: $.param($scope.pet)
			
		}).success(function(data) {
			
			//error
			if (data.status == 'failure') {
				$('.entry .error').text(data.message);
				$('.entry .error').show();
				return;
			}
			
			//update
			$scope.pets.push(data.value);
			
			//reset
			$scope.pet.name = '';
			$scope.pet.photo = '';
			$scope.pet.status = $scope.statuses[0];
			
			//scroll
			$('.rbody #rlast').get(0).scrollIntoView(false);
			
		});
	}
	
	/**
	 * delete
	 */
	$scope.delete = function(id) {
		
		$scope.clear();
		
		//delete
		$http({
			
			method: 'DELETE',
			url: 'pet/' + id
			
		}).success(function(data) {
			
			//error
			if (data.status == 'failure') {
				$('.rheader .rdelete img').attr('title', data.message);
				$('.rheader .rdelete').show();
				return;
			}
			
			//update
			$scope.pets = $.grep($scope.pets, function(e) { 
				return e.id != id; 
			});
			
		});
	}
	
	/**
	 * get
	 */
	$scope.get = function() {
		
		$scope.clear();
		
		$http({
			
			method: 'GET',
			url: 'pet'
			
		}).success(function(data) {
			
			$scope.pets = data.value;
			
		});
	}
	
	/**
	 * find
	 */
	$scope.find = function() {
		
		$scope.clear();
		
		//get
		$http({
			
			method: 'GET',
			url: 'pet/' + $scope.pet.id
			
		}).success(function(data) {
			
			//error
			if (data.status == 'failure') {
				$('.search .error').text(data.message);
				$('.search .error').show();
				return;
			}
			
			//success
			var row = $('.rbody #r' + $scope.pet.id);
			row.addClass('rselect');
			row.get(0).scrollIntoView();
			
		});
	}
	
	/**
	 * logout
	 */
	$scope.logout = function() {
		
		//post
		$http({
			
			method: 'POST',
			url: 'logout'
			
		}).success(function(data) {
			
			window.location = $location.path();
			
		});
	}
	
	/**
	 * refresh
	 */
	$scope.refresh = function() {
		
		window.location = $location.path();
		
	}
	
	/**
	 * user
	 */
	$scope.user = function() {
		
		//get
		$http({
			
			method: 'GET',
			url: 'user'
			
		}).success(function(data) {
			
			$scope.user = data.value;
			
		});
	}
	
});
