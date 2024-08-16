import { CanActivateFn } from '@angular/router';

export const redirectToLoginGuard: CanActivateFn = (route, state) => {
  return true;
};
