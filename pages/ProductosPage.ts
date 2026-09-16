import { type Locator, type Page } from '@playwright/test';

// Page Object de la pantalla de productos, la que aparece después de un login exitoso.
export class ProductosPage {
  // Título de la pantalla, que dice "Products". Es público para que la prueba lo revise.
  readonly titulo: Locator;

  // Recibe la pestaña y prepara el localizador del título.
  constructor(page: Page) {
    this.titulo = page.getByTestId('title');
  }
}
