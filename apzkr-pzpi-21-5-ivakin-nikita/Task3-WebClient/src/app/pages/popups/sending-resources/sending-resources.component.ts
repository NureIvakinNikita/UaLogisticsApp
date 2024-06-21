import { CommonModule } from '@angular/common';
import { ChangeDetectionStrategy, Component, Inject } from '@angular/core';
import { MatDialogTitle, MatDialogContent, MatDialogActions, MatDialogClose, MAT_DIALOG_DATA } from '@angular/material/dialog';

@Component({
  selector: 'app-sending-resources',
  templateUrl: './sending-resources.component.html',
  styleUrl: './sending-resources.component.scss',
  standalone: true,
  imports: [MatDialogTitle, MatDialogContent, MatDialogActions, MatDialogClose, CommonModule],
  changeDetection: ChangeDetectionStrategy.OnPush,
})
export class SendingResourcesComponent {
  constructor(@Inject(MAT_DIALOG_DATA) public data: { needForSupply: boolean; reourcesUpdated: boolean }) {}
}
