import { Component, inject, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { PreferencesDialogComponent } from '../preferences-dialog/preferences-dialog.component';
import { HttpService } from '../../Services/http.service';

@Component({
  selector: 'app-header',
  standalone: true,
  imports: [],
  templateUrl: './header.component.html',
  styleUrl: './header.component.scss'
})
export class HeaderComponent implements OnInit {
  allPreference: { id: number; name: string; }[] = [];
  _http = inject(HttpService);
  selectedPreference: { id: number; name: string; }[] = [];
  constructor(private dialog: MatDialog) {}
  ngOnInit(): void {
    this.getPreferences();
    this.getUserPreferences();
  }

  openPreferences(): void {
    const dialogRef = this.dialog.open(PreferencesDialogComponent, {
      width: '50rem',
      height:'25rem',
      data:{
        selectedPreferences:this.selectedPreference,
        allPreferences : this.allPreference
      }
    });

    dialogRef.afterClosed().subscribe((result:Array<number>) => {
      if (result) {
        this.updatePreference(result);
      }
    });
  }
  logout():void{
    localStorage.clear();
  }
  getPreferences():void{
    this._http.GET<Array<{id:number,name:string}>>("/api/preferences/all").subscribe({
      next: (preferences) => {
        this.allPreference = preferences;
      }
    })
  }

  getUserPreferences():void{
    this._http.GET<Array<{id:number,name:string}>>("/api/preferences").subscribe({
      next: (preferences) => {
        this.selectedPreference = preferences;
      }
    })
  }
  updatePreference(ids:Array<number>){
    this._http.PUT('/api/preferences/update',ids).subscribe();
  }
}
