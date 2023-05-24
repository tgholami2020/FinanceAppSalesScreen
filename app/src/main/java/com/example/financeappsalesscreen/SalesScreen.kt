package com.example.financeappsalesscreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.financeappsalesscreen.ui.theme.*

@Composable
fun SalesScreen(){
    Column (
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    listOf(CustomDarkGray, CustomLightGreen)
                )
            )
            .padding(25.dp)
            ){
        Row (
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
                ){
            IconButton(onClick = { /*TODO*/ }) {
                Icon(painter = painterResource(id = R.drawable.more),
                    contentDescription ="More",
                    tint= Color.White,
                    modifier = Modifier.size(30.dp)
                    )
            }
            Box(
                modifier = Modifier
                    .size(50.dp)
                    .border(
                        width = 1.dp,
                        shape = RoundedCornerShape(20f),
                        color = CustomOrange
                    ),
                contentAlignment = Alignment.Center
            ) {
                Image(modifier= Modifier
                    .clip(RoundedCornerShape(5.dp))
                    .size(40.dp),
                    painter = painterResource(id = R.drawable.david),
                    contentDescription ="Profile pic" )
            }
        }
        LazyRow(modifier = Modifier
            .height(250.dp),
        verticalAlignment = Alignment.Bottom
            )
        {
            items(year2022Sales){item->
                SingleBar(
                    monthlySalesData = item
                )
                Spacer(modifier = Modifier.width(15.dp))
            }
        }
        Text(modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 20.dp),
            text = "Sales Revenue",
            fontSize = 26.sp,
            color= Color.White,
            fontFamily = FontFamily(Font(R.font.ubuntu))
            )
        //the Row which contain two Column
        Row(modifier = Modifier.fillMaxWidth())
        {
            Column(modifier = Modifier
                .weight(1f)
                .fillMaxHeight()

            ){
                InfoCard(
                    modifier = Modifier.weight(8f),
                    icon = painterResource(R.drawable.ic_sales),
                    mainText = "230k",
                    subText = "Sales"
                    )
                Spacer(modifier = Modifier.weight(1f))               //give space

                InfoCard(
                    modifier = Modifier.weight(11f),
                    icon = painterResource(R.drawable.ic_wallet),
                    mainText = "1.423k",
                    subText = "Products"
                    )
                
            }
            Spacer(modifier = Modifier.width(20 .dp))                     //space between columns

            Column(modifier = Modifier
                .weight(1f)
                .fillMaxHeight()
            ){
                InfoCard(modifier = Modifier.weight(10f),
                    icon = painterResource(R.drawable.ic_user),
                    mainText = "8.549k",
                    subText = "Customer"
                    )
                Spacer(modifier = Modifier.weight(1f))                //give space

                InfoCard(modifier = Modifier.weight(8f),
                    icon = painterResource(R.drawable.ic_chart),
                    mainText = "$9745",
                    subText = "Revenue"
                    )
                Spacer(modifier = Modifier.weight(1f))
            }
        }
    }
}
@Composable
fun InfoCard(
    modifier: Modifier=Modifier,
    icon: Painter,
    mainText: String,
    subText:String
){
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(30.dp))
            .fillMaxWidth()
            .background(Color.White.copy(alpha = 0.1f)),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                painter = icon,
                contentDescription =mainText,
                 tint=Color.White,
                modifier = Modifier.size(25.dp)
                )

            Text(text = mainText,
                fontSize = 30.sp,
                fontFamily = FontFamily(Font(R.font.ubuntu)),
                fontWeight = FontWeight.Bold,
                color = Color.White,
                modifier = Modifier.padding(vertical = 10.dp )
                )

            Text(text = subText,
                fontSize = 14.sp,
                fontFamily = FontFamily(Font(R.font.ubuntu)),
                color=Color.White
                )
        }
    }
}
data class MonthlySalesData(
    var month: String,
    var salesInThousands:Int
)
val year2022Sales= mutableListOf(
    MonthlySalesData("Jan",13),
    MonthlySalesData("Feb",16),
    MonthlySalesData("Mar",20),
    MonthlySalesData("Apr",14),
    MonthlySalesData("May",12),
    MonthlySalesData("Jun",15),
    MonthlySalesData("Jul",20),
    MonthlySalesData("Aug",10),
    MonthlySalesData("Sep",18),
    MonthlySalesData("Oct",18),
    MonthlySalesData("Nov",12),
    MonthlySalesData("Des",13)

)

@Composable
fun SingleBar(
    monthlySalesData: MonthlySalesData
){
    val largestSalesValue = year2022Sales.maxOf {
        it.salesInThousands
    }
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "${monthlySalesData.salesInThousands}k",
             fontSize = 12.sp,
             color = Color.White,
             fontFamily = FontFamily(Font(R.font.ubuntu)),

            )
        Spacer(modifier = Modifier.height(15.dp))
        Box(modifier = Modifier
            .clip(
                RoundedCornerShape(
                    topStart = 10.dp,
                    topEnd = 10.dp
                )
            )
            .background(
                if (year2022Sales.indexOf(monthlySalesData) % 2 == 0) {
                    CustomLightOrange
                } else CustomCyan
            )
            .width(40.dp)
            .height(
                (200 * monthlySalesData.salesInThousands / largestSalesValue).dp
            )
        ) {
            
        }
        Spacer(modifier = Modifier.height(5.dp))
        Text(text = monthlySalesData.month,
            color=Color.White,
            fontFamily = FontFamily(Font(R.font.ubuntu)),
            fontSize = 12.sp

            )
    }
}

@Preview
@Composable
fun Prev(){
    SalesScreen()
}