'use strict';

picarApp.controller('driveCtrl',
    function driveCtrl($scope, $http) {
        $scope.isLeft = false;
        $scope.isRight = false;
        $scope.isForward = false;
        $scope.isBack = false;

        $scope.left = function() {
            $scope.isLeft = true;
            $scope.isRight = false;

            sendState();
        };

        $scope.right = function() {
            $scope.isRight = true;
            $scope.isLeft = false;

            sendState();
        };

        $scope.straight = function() {
            $scope.isLeft = false;
            $scope.isRight = false;

            sendState();
        };

        $scope.forward = function() {
            $scope.isForward = true;
            $scope.isBack = false;

            sendState();
        };

        $scope.back = function() {
            $scope.isBack = true;
            $scope.isForward = false;

            sendState();
        };

        $scope.stop = function() {
            $scope.isForward = false;
            $scope.isBack = false;

            sendState();
        };

        function sendState() {
            var state = {
                turn: $scope.isLeft ? "LEFT" : $scope.isRight ? "RIGHT" : "STRAIGHT",
                throttle: $scope.isForward ? "FORWARD" : $scope.isBack ? "BACK" : "STOP"
            };

            $http.post('/api/car', state);
        }
    }
);