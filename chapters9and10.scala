//Brianna Morreale
//11/11/2022

import doodle.core._
import doodle.image._
import doodle.image.syntax._
import doodle.image.syntax.core._
import doodle.java2d._

object flower{

    def parametricCircle(angle: Angle): Point =
        Point.cartesian(angle.cos, angle.sin)
  
    def rose(angle: Angle): Point =
        Point.cartesian((angle * 10).cos * angle.cos, (angle * 10).cos * angle.sin)

    def scale(factor: Double): Point => Point = 
        (pt: Point) => {
        Point.polar(pt.r * factor, pt.angle)
    }

    def createDrawing(start: Angle, samples: Int, location: Angle => Point): Image = {
        val step = Angle.one / samples
        val dot = Image.circle(40).strokeColor(Color.red)
        def loop(count: Int): Image = {
            val angle = step * count
            count match {
                case 0 => Image.empty
                case n => dot.at(location(angle).toVec) on loop(n - 1)
                }
        }
    
    loop(samples)
    }

    def locate(scale: Point => Point, point: Angle => Point): Angle => Point =
        (angle: Angle) => scale(point(angle))

    
    val flower = {
        createDrawing(30.degrees, 600, locate(scale(200), rose _)) 
    }


    def main(args: Array[String]): Unit = {
        flower.draw()
  }
}