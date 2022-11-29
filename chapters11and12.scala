//Brianna Morreale
//11/13/2022

import doodle.core._
import doodle.image._
import doodle.image.syntax._
import doodle.image.syntax.core._
import doodle.java2d._
import doodle.core.PathElement._
import doodle.turtle._
import doodle.turtle.Instruction._



object drawing{

    //this is practice code
    val y = Turtle.draw(List(
          forward(100),
          branch(turn(45.degrees), forward(100)),
          branch(turn(-45.degrees), forward(100)))
        )

    val stepSize = 10
    // stepSize: Int = 10

    def rule(i: Instruction): List[Instruction] =
    i match {
        case Forward(_) => List(forward(stepSize), forward(stepSize))
        case NoOp => 
        List(branch(turn(45.degrees), forward(stepSize), noop), 
            branch(turn(-45.degrees), forward(stepSize), noop))
        case other => List(other)
    }
    
    
   // List[A] flatMap (A => List[B]) = List[B]

    def double[A](in: List[A]): List[A] =
        in.flatMap { x => List(x, x) }

    def rewrite(instructions: List[Instruction], rule: Instruction => List[Instruction]): List[Instruction] =
        instructions.flatMap { i =>
            i match {
            case Branch(i) =>
                List(branch(rewrite(i, rule):_*))
            case other =>
                rule(other)
            }
        }

    def squareSpiral(steps: Int, distance: Double, angle: Angle, increment: Double): Image = {
        Turtle.draw((1 to steps).toList.flatMap { n =>
         List(forward(distance + (n * increment)), turn(angle)) 
        })
}

    //the actual code
    def triangles(sides: Int, sideLength: Double): Image = {
    val rotation = Angle.one / sides
    
    Turtle.draw((1 to sides).toList.flatMap { n =>
        List(turn(rotation), forward(sideLength), forward(sideLength + (n * sideLength)), turn(95.degrees))
    })
    }

     def main(args: Array[String]): Unit = {
       triangles(50,9).draw()
     }
        
}