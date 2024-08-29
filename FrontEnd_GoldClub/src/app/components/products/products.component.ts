import { CommonModule } from '@angular/common';
import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-products',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './products.component.html',
  styleUrls: ['./products.component.css']
})
export class ProductsComponent implements OnInit {
  products: any[] = [];
  loading: boolean = false;

  constructor(private http: HttpClient, private router: Router) {}

  ngOnInit() {
    this.loadProducts();
  }

  loadProducts() {
    const token = localStorage.getItem('token');
    if (token) {
      this.loading = true;
      this.http.get('https://goldclub-production.up.railway.app/api/productos', {
        headers: { Authorization: `Bearer ${token}` }
      }).subscribe((data: any) => {
        this.products = data;
        this.loading = false;
      }, error => {
        this.loading = false;
        if (error.status === 401) {
          alert('No autorizado. Redirigiendo al login...');
          this.router.navigate(['/login']);
        } else {
          alert('Error al cargar los productos. Intenta de nuevo más tarde.');
        }
      });
    } else {
      alert('No autorizado. Redirigiendo al login...');
      this.router.navigate(['/login']);
    }
  }

  eliminarProducto(id: number) {
    const token = localStorage.getItem('token');
    if (token) {
      if (confirm('¿Estás seguro de que deseas eliminar este producto?')) {
        this.http.delete(`https://goldclub-production.up.railway.app/api/productos/${id}`, {
          headers: { Authorization: `Bearer ${token}` }
        }).subscribe(() => {
          this.products = this.products.filter(product => product.id !== id);
          alert('Producto eliminado correctamente');
        }, error => {
          alert('Error al eliminar el producto. Intenta de nuevo más tarde.');
        });
      }
    } else {
      alert('No autorizado. Redirigiendo al login...');
      this.router.navigate(['/login']);
    }
  }
}
