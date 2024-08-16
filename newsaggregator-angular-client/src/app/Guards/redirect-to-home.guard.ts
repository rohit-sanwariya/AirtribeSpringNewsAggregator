import { inject } from '@angular/core';
import { CanActivateFn, Router } from '@angular/router';

export const redirectToHomeGuard: CanActivateFn = (route, state) => {
  if(localStorage.getItem('authLogin')) {inject(Router).navigate(["/app"]);return false;}
  return true;
};
