import { type Locator, type Page } from '@playwright/test';

// Page Object de la pantalla de inicio de sesión de saucedemo.com.
// Es el control remoto de esa pantalla: las pruebas usan sus métodos y no escriben localizadores.
export class LoginPage {
  // Pestaña del navegador con la que trabaja la página. La recibe el constructor.
  private readonly page: Page;

  // Campo donde se escribe el usuario. Lo buscamos por el texto gris que muestra adentro.
  private readonly campoUsuario: Locator;

  // Campo donde se escribe la clave, también por su texto gris.
  private readonly campoClave: Locator;

  // Botón "Login". Lo buscamos como lo ve una persona: un botón que dice Login.
  private readonly botonLogin: Locator;

  // Mensaje rojo que sale cuando el login falla. Es público para que la prueba pueda revisar su texto.
  readonly mensajeError: Locator;

  // Recibe la pestaña y prepara los localizadores. Preparar un localizador no busca nada todavía.
  constructor(page: Page) {
    this.page = page;
    this.campoUsuario = page.getByPlaceholder('Username');
    this.campoClave = page.getByPlaceholder('Password');
    this.botonLogin = page.getByRole('button', { name: 'Login' });
    this.mensajeError = page.getByTestId('error');
  }

  // Abre la pantalla de login. La dirección completa sale de baseURL en playwright.config.ts.
  async abrir() {
    await this.page.goto('/');
  }

  // Escribe el usuario y la clave y pulsa Login.
  async iniciarSesion(usuario: string, clave: string) {
    await this.campoUsuario.fill(usuario);
    await this.campoClave.fill(clave);
    await this.botonLogin.click();
  }
}
