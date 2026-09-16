// Datos de prueba del login. Están aparte para que las pruebas no tengan usuarios y claves escritos a mano.

// Forma de un usuario: siempre tiene nombre y clave, y los dos son texto.
export type Usuario = {
  nombre: string;
  clave: string;
};

// Forma de un caso de login que debe fallar: qué se escribe y qué mensaje debe salir.
export type LoginInvalido = {
  descripcion: string;
  nombre: string;
  clave: string;
  mensajeEsperado: string;
};

// Usuario que entra sin problema.
export const usuarioEstandar: Usuario = { nombre: 'standard_user', clave: 'secret_sauce' };

// Usuario que la tienda tiene bloqueado.
export const usuarioBloqueado: Usuario = { nombre: 'locked_out_user', clave: 'secret_sauce' };

// Casos de login inválido. La prueba se repite una vez por cada elemento de esta lista.
export const loginsInvalidos: LoginInvalido[] = [
  {
    descripcion: 'clave incorrecta',
    nombre: 'standard_user',
    clave: 'clave_mala',
    mensajeEsperado: 'Epic sadface: Username and password do not match any user in this service',
  },
  {
    descripcion: 'usuario vacío',
    nombre: '',
    clave: 'secret_sauce',
    mensajeEsperado: 'Epic sadface: Username is required',
  },
  {
    descripcion: 'clave vacía',
    nombre: 'standard_user',
    clave: '',
    mensajeEsperado: 'Epic sadface: Password is required',
  },
];
