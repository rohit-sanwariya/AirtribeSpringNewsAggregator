import { TestBed } from '@angular/core/testing';
import { CanActivateFn } from '@angular/router';

import { redirectToLoginGuard } from './redirect-to-login.guard';

describe('redirectToLoginGuard', () => {
  const executeGuard: CanActivateFn = (...guardParameters) => 
      TestBed.runInInjectionContext(() => redirectToLoginGuard(...guardParameters));

  beforeEach(() => {
    TestBed.configureTestingModule({});
  });

  it('should be created', () => {
    expect(executeGuard).toBeTruthy();
  });
});
