//Brianna Morreale
//11/4/2022

import doodle.core._
import doodle.image._
import doodle.image.syntax._
import doodle.image.syntax.core._
import doodle.java2d._

object fibAndChessboard{

    def Fib(count: Int): Int =
        count match{    
            case 0 => 0
            case 1 => 1
            case n => Fib(n-1) + Fib(n-2)

        }
    
    def sierpinski(count: Int): Image = {
        val triangle = Image.triangle(10, 10).strokeColor(Color.blue)
        count match {
            case 0 => triangle.above(triangle.beside(triangle))
            case n =>
            val unit = sierpinski(n-1)
            unit.above(unit.beside(unit))
        }
    }

    def sierpinski2(count: Int): Image = {
        val triangle2 = Image.triangle(10, 10).strokeColor(Color.red)
        count match {
            case 0 => triangle2.below(triangle2.beside(triangle2))
            case n =>
            val unit = sierpinski2(n-1)
            unit.above(unit.beside(unit))
        }
    }


    def chessboard(count: Int): Image = {
        val sq1 = sierpinski(2)

        val sq2 = sierpinski2(2)

        val base =
            (sq2.beside(sq1)).above(sq1.beside(sq2))
        count match {
            case 0 => base
            case n =>
            val unit = chessboard(n-1)
            (unit.beside(unit)).above(unit.beside(unit))
        }
        }

    def main(args: Array[String]): Unit = {

        chessboard(1).draw()
        
        var counter:Int=0;

        while(counter <= 50){
            println(Fib(counter))
            counter = counter+1
        }


  }

}
  