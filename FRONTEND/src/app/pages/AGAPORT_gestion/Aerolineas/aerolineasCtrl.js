(function () {
    'use strict';
  
    angular.module('Agaport.gestion.aerolineas')
        .controller('AerolineasCtrl', AerolineasCtrl);
  
    /** @ngInject */
    function AerolineasCtrl($scope, $filter, editableOptions, editableThemes,$http,$uibModal,baProgressModal) {
  
      $scope.smartTablePageSize = 10;
      $scope.aerolineaSeleccionado=[];
      
      $scope.datosUsuarios='';

      $http({
        method:'GET',
        url: globalBackendLink + '/aerolineas/listar'
      }).then(function successCallback(response) {
        console.log('CARLOS');
        console.log(response.data);
        $scope.datosAerolineas = response.data;

      },function errorCallback(response) {
        console.log('error en obtener data de aerolineas de ' + globalBackendLink);
      });
  
      $scope.removePuerta = function(index) {
        $scope.users.splice(index, 1);
      };
  
      $scope.addPuerta = function() {
        $scope.inserted = {
          id: $scope.users.length+1,
          name: '',
          status: null,
          group: null
        };
        $scope.users.push($scope.inserted);
      };

      $scope.eliminarAerolinea = function(idAerolinea) {
      var variable_entrega={"idAerolinea":idAerolinea};
      $http({
        method:'POST',
        url: globalBackendLink + '/aerolineas/eliminar',
        data: $.param(variable_entrega),
        headers:{
          'Content-Type':'application/x-www-form-urlencoded; charset=UTF-8'
        }
      }).then(function successCallback(response) {
        console.log("exito");
      },function errorCallback(response) {
        console.log('error en obtener aerolinea de '+globalBackendLink);
      });
      };
  
      editableOptions.theme = 'bs3';
      editableThemes['bs3'].submitTpl = '<button type="submit" class="btn btn-primary btn-with-icon"><i class="ion-checkmark-round"></i></button>';
      editableThemes['bs3'].cancelTpl = '<button type="button" ng-click="$form.$cancel()" class="btn btn-default btn-with-icon"><i class="ion-close-round"></i></button>';
		
      $scope.open = function (page, size) {
        $uibModal.open({
          animation: true,
          templateUrl: page,
          size: size,
          resolve: {
            items: function () {
              return $scope.items;
            }
          }
        });
      };
      $scope.openProgressDialog = baProgressModal.open; 
	  $scope.seleccionarAerolinea=function(aerolinea){
		  $scope.aerolineaseleccionada=aerolinea;
	  }
	  
    }
	
    angular.module('Agaport.gestion.puertas_mangas_zonas')
        .controller('AerolineasNuevoCtrl', AerolineasNuevoCtrl);
  
    /** @ngInject */
    function AerolineasNuevoCtrl($scope, $filter, editableOptions, editableThemes,$http,$uibModal,baProgressModal) {
      console.log('controlador nuevo');
      
      var contro = this;

      $scope.disabled = undefined;
      $scope.hols='hola';

      $scope.standardItem = {};
      $scope.standardSelectItems = [
        {label: 'Option 1', value: 1},
        {label: 'Option 2', value: 2},
        {label: 'Option 3', value: 3},
        {label: 'Option 4', value: 4}
      ];

      $scope.registrarAerolinea=function(nombres,prioridad){
        var link_header='http://200.16.7.178:8080';
        var variable_entrega={"Nombre": nombres,"idPrioridad":prioridad};
        // $http.post(link_header+'/usuarios/insertar',variable_entrega,{responseType:'text'}).success(function(response){
        //   console.log('post usuario success');
        //   console.log(response);
        //   $state.go('agaport_gestion.usuarios');
        // });
        $http({
          url: link_header + '/aerolineas/insertar',
          method: 'POST',
          data: $.param(variable_entrega),
          headers:{
            'Content-Type':'application/x-www-form-urlencoded; charset=UTF-8'
          }
        }).then(function() {
          console.log('post aerolinea success');
          $state.go('agaport_gestion.aerolineas');
        },function(response){
          console.log('error POST');
          console.log(response);
        });
      }

    }

    angular.module('Agaport.gestion.puertas_mangas_zonas')
        .controller('AerolineasModificarCtrl', AerolineasModificarCtrl);
  
    /** @ngInject */
    function AerolineasModificarCtrl($scope, $filter, editableOptions, editableThemes,$http,$uibModal,baProgressModal) {
      console.log('controlador nuevo');
      
      var contro = this;

      $scope.disabled = undefined;
      $scope.hols='hola';

      $scope.standardItem = {};
      $scope.standardSelectItems = [
        {label: 'Option 1', value: 1},
        {label: 'Option 2', value: 2},
        {label: 'Option 3', value: 3},
        {label: 'Option 4', value: 4}
      ];

    }

  })();
  