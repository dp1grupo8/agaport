(function () {
    'use strict';
  
    angular.module('Agaport.servicios', [])
        .service('listarAerolineas', function(){
            var datosAerolineas=[];
            
            $http({
                method:'GET',
                url: globalBackendLink + '/aerolineas/listar'
              }).then(function successCallback(response) {
                datosAerolineas = response.data;
              },function errorCallback(response) {
                console.log('error en obtener data de aerolineas de ' + globalBackendLink);
              });

            return datosAerolineas;
        });
  
  })();
  