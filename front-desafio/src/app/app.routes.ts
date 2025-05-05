import { Routes } from '@angular/router';
import { HomeComponent } from './pages/home/home.component';
import { HeaderLayoutComponent } from './components/header-layout/header-layout.component';
import { LoginComponent } from './pages/login/login.component';
import { financeiroComponent } from './pages/financeiro/financeiro.component';

export const routes: Routes = [
    { path: 'login', component: LoginComponent }, // Tela de login sem o header
    {
      path: '',
      component: HeaderLayoutComponent,
      children: [
        { path: 'home', component: HomeComponent },
        { path: 'financeiro', component: financeiroComponent },
        { path: '', redirectTo: '/home', pathMatch: 'full' }
      ]
    }
  ]
