(function () {
    'use strict';

    angular.module('BlurAdmin.pages.mi_pagina')
        .controller('mi_paginaCtrl', mi_paginaCtrl);

    /** @ngInject */
    function mi_paginaCtrl($scope, $http, $timeout) {
        $scope.simulacionIni = false;
        $scope.llamarSimulacion = function () {
            console.log('iniciando simulación 2');
            $http.get('http://200.16.7.178/backendAGAPORT/simulacion/iniciar').then(function successCallback(response) {
                console.log('simulacion iniciada');
            }, function errorCallback(response) {
                console.log(response);
            });
        }

        $scope.iniciarSimulacion = function () {
            console.log('iniciando simulación 1');
            if (!$scope.simulacionIni) {
                $scope.simulacionIni = true;
                $scope.llamarSimulacion();
                setInterval($scope.llamarSimulacion, 60000);
            }
        }

        $scope.progressFunction = function () {
            return $timeout(function () { }, 3000);
        };
    }

})();