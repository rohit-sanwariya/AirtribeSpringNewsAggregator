import { CanActivateFn } from '@angular/router';

export const redirectToHomeGuard: CanActivateFn = (route, state) => {
  return false;
};
