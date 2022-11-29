//Brianna Morreale
//10/28/2022

import doodle.core._
import doodle.image._
import doodle.image.syntax._
import doodle.image.syntax.core._
import doodle.java2d._

object mouseDrawing{

  def grid(count: Int): Image = {
  val sq1 = 
    Image
      .rectangle(30, 30).noFill
      .under(Image.circle(50).noStroke.fillColor(Color.red))
      .beside(Image.circle(50).noStroke.fillColor(Color.red))
      .above(Image.circle(50).noStroke.noFill)
      .on(Image.circle(20).noStroke.noFill
      .beside(Image.circle(20).noStroke.fillColor(Color.green))
      .beside(Image.circle(20))).noStroke.on(Image.circle(60).noStroke.fillColor(Color.red))
  val sq2   = 
    Image
      .rectangle(30, 30).noFill
      .under(Image.circle(50).noStroke.fillColor(Color.red))
      .beside(Image.circle(50).noStroke.fillColor(Color.red))
      .above(Image.circle(50).noStroke.noFill)
      .on(Image.circle(20).noStroke.noFill
      .beside(Image.circle(20).noStroke.fillColor(Color.green))
      .beside(Image.circle(20))).noStroke.on(Image.circle(60).noStroke.fillColor(Color.red))

  val base =
    (sq2.beside(sq1)).above(sq1.beside(sq2))
  count match {
    case 0 => base
    case n =>
      val unit = grid(n-1)
      (unit.beside(unit)).above(unit.beside(unit))
      
  }
}

//method to draw the mouse
class mouse()  {
  //using abstraction to name the mouse drawing
  val mouse =
   Image
    .circle(50).noStroke.fillColor(Color.red)
    .beside(Image.circle(50).noStroke.fillColor(Color.red))
    .above(Image.circle(50).noStroke.noFill)
    .on(Image.circle(20).noStroke.noFill
    .beside(Image.circle(20).noStroke.fillColor(Color.green))
    .beside(Image.circle(20))).noStroke.on(Image.circle(60).noStroke.fillColor(Color.red))

  grid(1).draw()
  
}
      
  def main(args: Array[String]): Unit = {
    val mickey = new mouse()

  }
  
}
