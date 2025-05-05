import { Injectable, inject } from '@angular/core';
import { CanActivateFn, ActivatedRouteSnapshot, Router } from '@angular/router';
import { AuthService } from '../app/components/auth/auth.service';

export const authGuard: CanActivateFn = (route: ActivatedRouteSnapshot) => {
  const authService = inject(AuthService);
  const router = inject(Router);

  const expectedRoles: string[] = route.data['roles'] || [];
  const userRoles = authService.getUserRoles();

  const isAuthorized = expectedRoles.length === 0 || expectedRoles.some(role => userRoles.includes(role));

  if (authService.isAuthenticated() && isAuthorized) {
    return true;
  }

  router.navigate(['/login']);
  return false;
};
