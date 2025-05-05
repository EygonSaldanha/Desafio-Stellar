import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { FlexLayoutModule } from '@angular/flex-layout';
import { MatButtonModule } from '@angular/material/button';
import { MatTableModule } from '@angular/material/table';
import { MatIconModule } from '@angular/material/icon';
import { MatCardModule } from '@angular/material/card';
import { MatInputModule } from '@angular/material/input';
import { decodeToken } from '../../../utils/jwt.util';
import { Router } from '@angular/router';

@Component({
  selector: 'app-home',
    imports: [
      MatInputModule,
      MatCardModule,
      MatIconModule,
      MatTableModule,
      MatButtonModule,
      FlexLayoutModule,
      FormsModule,
      CommonModule
    ],
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss']
})
export class HomeComponent implements OnInit {
  data: any[] = [];
  nomeBusca: string = '';
  displayedColumns: string[] = ['coluna1', 'coluna2', 'coluna3'];

  constructor(private http: HttpClient,private router: Router) {}

  ngOnInit(): void {
    this.fetchData();
    console.log('Token decodificado:', decodeToken());
    
  }

  fetchData(): void {
    this.http.get<any[]>('https://api.exeadadasmplo.com/dados').subscribe(
      (response) => {
        this.data = response;
      },
      (error) => {
        console.error('Erro ao buscar dados:', error);
      }
    );
  }

  hasRole(role: string): boolean {
      const decoded: any = decodeToken();
      return decoded.groups.includes(role);
  }
  
  pesquisar(): void {
    console.log('Buscando por:', this.nomeBusca);
    // Adicione a lógica de busca aqui
  }

  onMenu1Click(): void {
    this.router.navigate(['/financeiro']);
  }

  onMenu2Click(): void {
    console.log('Menu 2 clicado');
  }

  onMenu3Click(): void {
    console.log('Menu 3 clicado');
  }

  onMenu4Click(): void {
    console.log('Menu 4 clicado');
  }
}
