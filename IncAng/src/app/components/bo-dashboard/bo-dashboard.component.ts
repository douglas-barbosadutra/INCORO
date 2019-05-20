import { Component, OnInit } from '@angular/core';
import { Router } from "@angular/router";
import { CoopThingService} from '../../services/coopThingService.service';
import { CoopDTO} from "src/dto/CoopDTO"

declare var $: any;
declare var arbor: any;

@Component({
  selector: 'app-bo-dashboard',
  templateUrl: './bo-dashboard.component.html',
  styleUrls: ['./bo-dashboard.component.css']
})
export class BoDashboardComponent implements OnInit {
  private sys;
  public coopList: Array<CoopDTO>;

  constructor(private router : Router,private coopThingService:CoopThingService) {
    this.router.onSameUrlNavigation = 'reload';

   }

  ngOnInit() {
    this.getTaskScheduledList();

    var Renderer = function (canvas) {
      var canvas = $(canvas).get(0);
      var ctx = canvas.getContext("2d");
      var particleSystem;
      var that = {
              init: function (system) {
                      particleSystem = system;
                      particleSystem.screenSize(canvas.width, canvas.height);
                      particleSystem.screenPadding(100);
                      that.initMouseHandling()
              },
              redraw: function () {
                      ctx.fillStyle = "white";
                      ctx.fillRect(0, 0, canvas.width, canvas.height);
                      particleSystem.eachEdge(function (edge, pt1, pt2) {
                              ctx.strokeStyle = edge.data.linkcolor;
                              ctx.lineWidth = 3;
                              ctx.beginPath();
                              ctx.moveTo(pt1.x, pt1.y);
                              ctx.lineTo(pt2.x, pt2.y);
                              ctx.stroke();
                      });
                      particleSystem.eachNode(function (node, pt) {
                              ctx.beginPath();
                              ctx.arc(pt.x, pt.y, 15, 0, 2 * Math.PI);
                              ctx.fillStyle = node.data.nodecolor;
                              ctx.fill();
                              ctx.font = "18px Arial";
                              ctx.fillStyle = "#000000";
                              ctx.fillText(node.data.name, pt.x + 20, pt.y + 5);
                      });
              },
              initMouseHandling: function () {
                      var dragged = null;
                      var handler = {
                              clicked: function (e) {
                                      var pos = $(canvas).offset();
                                      var _mouseP = arbor.Point(e.pageX - pos.left, e.pageY - pos.top);
                                      dragged = particleSystem.nearest(_mouseP);

                                      //console.log(dragged.node);
                                      //console.log(dragged.node.name);
                                      $('#task-selected').val(dragged.node.name);

                                      if (dragged && dragged.node !== null) {
                                              dragged.node.fixed = true;
                                      }
                                      $(canvas).bind('mousemove', handler.dragged);
                                      $(window).bind('mouseup', handler.dropped);
                                      return false;
                              },
                              dragged: function (e) {
                                      var pos = $(canvas).offset();
                                      var s = arbor.Point(e.pageX - pos.left, e.pageY - pos.top);
                                      if (dragged && dragged.node !== null) {
                                              var p = particleSystem.fromScreen(s);
                                              dragged.node.p = p
                                      }
                                      return false;
                              },
                              dropped: function (e) {
                                      if (dragged === null || dragged.node === undefined) return;
                                      if (dragged.node !== null) dragged.node.fixed = false;
                                      dragged.node.tempMass = 1000;
                                      dragged = null;
                                      $(canvas).unbind('mousemove', handler.dragged);
                                      $(window).unbind('mouseup', handler.dropped);
                                      var _mouseP = null;
                                      return false;
                              }
                      };
                      $(canvas).mousedown(handler.clicked);
              }
      }
      return that;
    }

    this.sys = arbor.ParticleSystem(700, 700, 0.5);
    this.sys.parameters({gravity:true});
    this.sys.renderer = Renderer("#viewport");


  }

  getTaskScheduledList() {
    this.coopThingService.showCoop().subscribe((data : any) => {
      if (data !=null) {
        this.coopList = new Array();
        this.coopList = data;
        console.log("XXXXXXXX");
        for ( let coop of this.coopList){
          this.sys.addNode(coop.tone, { name:"id thing:"+coop.tone ,nodecolor: "#0000ff"});
          this.sys.addNode(coop.ttwo, {  name:"id thing:"+coop.ttwo,nodecolor: "#0000ff"});
          this.sys.addEdge(coop.tone, coop.ttwo, {linkcolor: "#808080"})
        console.log(coop);
        console.log(coop.tone);
        }
      }
    });
  }

}
