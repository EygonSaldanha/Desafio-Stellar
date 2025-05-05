import { Component, OnInit, inject } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MatInputModule } from '@angular/material/input'
import { MatCardModule } from '@angular/material/card'
import { FlexLayoutModule } from '@angular/flex-layout'
import { MatIconModule } from '@angular/material/icon'
import { FormsModule } from '@angular/forms'
import { MatTableModule } from '@angular/material/table'
import { MatButtonModule } from '@angular/material/button';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Router } from '@angular/router';
import { Transacao } from '../../models/transacao';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-financeiro',
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
  templateUrl: './financeiro.component.html',
  styleUrl: './financeiro.component.scss'
})
export class financeiroComponent implements OnInit {

  nomeBusca: string = '';
  listaTransacoes: Transacao[] = [];
  colunasTable: string[] = ["id", "valor", "contaOrigemNumero", "contaOrigemNomeUsuario", "contaDestinoNumero", "contaDestinoNomeUsuario"];
  
  snack: MatSnackBar = inject(MatSnackBar);

  constructor(
    private router: Router,
    private http: HttpClient
  ){ }

  ngOnInit() {
    this.http.get<Transacao[]>('http://localhost:8089/transacao/conta/3')
      .subscribe((dados) => this.listaTransacoes = dados);
  }

  onSquareButtonClick(): void {
    console.log('Botão quadrado clicado!');
  }

  preparaEditar(id: string){
    this.router.navigate(['/cadastro'], { queryParams: { "id": id } } )
  }

}
