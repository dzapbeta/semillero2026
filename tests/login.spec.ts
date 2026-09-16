import { test, expect } from '@playwright/test';
import { LoginPage } from '../pages/LoginPage';
import { ProductosPage } from '../pages/ProductosPage';
import { usuarioEstandar, usuarioBloqueado, loginsInvalidos } from '../data/usuarios';

// Pruebas del inicio de sesión en saucedemo.com. Son los mismos flujos de la clase 2, ahora con Playwright y POM.
// test.describe agrupa las pruebas bajo un nombre que ves en la terminal, en el reporte y en VS Code.
test.describe('Inicio de sesión en SauceDemo', () => {
  // Un usuario válido entra y ve la pantalla de productos.
  // Playwright le entrega a cada prueba una pestaña nueva y limpia llamada page.
  test('login exitoso con usuario estándar', async ({ page }) => {
    const login = new LoginPage(page);
    await login.abrir();
    await login.iniciarSesion(usuarioEstandar.nombre, usuarioEstandar.clave);

    const productos = new ProductosPage(page);
    await expect(productos.titulo).toHaveText('Products');
  });

  // Un usuario bloqueado no entra y ve el mensaje de bloqueo.
  test('login con usuario bloqueado muestra mensaje de error', async ({ page }) => {
    const login = new LoginPage(page);
    await login.abrir();
    await login.iniciarSesion(usuarioBloqueado.nombre, usuarioBloqueado.clave);

    await expect(login.mensajeError).toHaveText('Epic sadface: Sorry, this user has been locked out.');
  });

  // Data driven: el for crea una prueba por cada caso de la lista loginsInvalidos.
  for (const caso of loginsInvalidos) {
    // El nombre de cada prueba incluye la descripción del caso, por ejemplo "login inválido: clave vacía".
    test(`login inválido: ${caso.descripcion}`, async ({ page }) => {
      const login = new LoginPage(page);
      await login.abrir();
      await login.iniciarSesion(caso.nombre, caso.clave);

      await expect(login.mensajeError).toHaveText(caso.mensajeEsperado);
    });
  }
});
