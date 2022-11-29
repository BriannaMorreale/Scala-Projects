//Brianna Morreale
//10/21/2022
//Here is my best attempt
import doodle.core._
import doodle.image._
import doodle.image.syntax._
import doodle.image.syntax.core._
import doodle.java2d._

object mouse {
  val image =

   Image
    .circle(50).noStroke.fillColor(Color.red)
    .beside(Image.circle(50).noStroke.fillColor(Color.red))
    .above(Image.circle(50).noStroke.noFill)
    .on(Image.circle(20).noStroke.noFill
    .beside(Image.circle(20).noStroke.fillColor(Color.green))
    .beside(Image.circle(20))).noStroke.on(Image.circle(60).noStroke.fillColor(Color.red))
    
    
       

      
  def main(args: Array[String]): Unit = {
    image.draw()
  }
  }
