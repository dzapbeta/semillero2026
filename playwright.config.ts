import { defineConfig, devices } from '@playwright/test';

// Configuración de Playwright para todo el proyecto.
// Lo que pongas aquí aplica a todas las pruebas, así no lo repites en cada archivo.
export default defineConfig({
  // Carpeta donde Playwright busca los archivos de prueba (los que terminan en .spec.ts).
  testDir: './tests',

  // Reportes: "list" muestra cada prueba en la terminal y "html" arma una página que abres con npm run reporte.
  // open: 'never' evita que el reporte se abra solo y deje la terminal ocupada.
  reporter: [['list'], ['html', { open: 'never' }]],

  // Opciones que usan todas las pruebas.
  use: {
    // Dirección de la aplicación. Con esto, page.goto('/') abre https://www.saucedemo.com/
    baseURL: 'https://www.saucedemo.com',

    // saucedemo marca sus elementos con el atributo data-test. Así getByTestId('error') busca data-test="error".
    testIdAttribute: 'data-test',

    // Si una prueba falla, guarda una captura de pantalla de cómo quedó la página.
    screenshot: 'only-on-failure',

    // Si una prueba falla, guarda la traza: una grabación paso a paso para ver qué pasó.
    trace: 'retain-on-failure',
  },

  // Navegadores donde corren las pruebas. Para empezar usamos solo Chromium.
  projects: [
    {
      name: 'chromium',
      use: { ...devices['Desktop Chrome'] },
    },
  ],
});
