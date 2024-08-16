import { inject } from '@angular/core';
import { CanActivateFn, Router } from '@angular/router';

export const redirectToLoginGuard: CanActivateFn = (route, state) => {
  if(!localStorage.getItem('authLogin')) {inject(Router).navigate(["/login"]);return true;}
  return false;
};
