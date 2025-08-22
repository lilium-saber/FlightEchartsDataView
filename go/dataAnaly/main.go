package main

import (
	"context"
	"dataAnaly/proto"
	"net"

	"google.golang.org/grpc"
	"gorm.io/driver/mysql"
	"gorm.io/gorm"
)

type server struct {
	proto.UnimplementedFlightServiceServer
	db *gorm.DB
}

type MainDelayReasonRate struct {
	Year                  int     `json:"Year"`
	Month                 int     `json:"Month"`
	NumCarrierDelays      int     `json:"NumCarrierDelays"`
	NumWeatherDelays      int     `json:"NumWeatherDelays"`
	NumNASDelays          int     `json:"NumNASDelays"`
	NumSecurityDelays     int     `json:"NumSecurityDelays"`
	NumLateAircraftDelays int     `json:"NumLateAircraftDelays"`
	TotalDelays           int     `json:"TotalDelays"`
	CarrierDelayRate      float64 `json:"CarrierDelayRate"`
	WeatherDelayRate      float64 `json:"WeatherDelayRate"`
	NASDelayRate          float64 `json:"NASDelayRate"`
	SecurityDelayRate     float64 `json:"SecurityDelayRate"`
	LateAircraftDelayRate float64 `json:"LateAircraftDelayRate"`
}

func (MainDelayReasonRate) TableName() string {
	return "mainDelayReasonRate"
}

func (s *server) GetMainDelayReasonRates(ctx context.Context, in *proto.Empty) (*proto.MainDelayReasonRateList, error) {
	var dbResults []MainDelayReasonRate
	s.db.Order("Month desc").Find(&dbResults)

	var pbResults []*proto.MainDelayReasonRate
	for _, r := range dbResults {
		pbResults = append(pbResults, &proto.MainDelayReasonRate{
			Year:                  int32(r.Year),
			Month:                 int32(r.Month),
			NumCarrierDelays:      int32(r.NumCarrierDelays),
			NumWeatherDelays:      int32(r.NumWeatherDelays),
			NumNASDelays:          int32(r.NumNASDelays),
			NumSecurityDelays:     int32(r.NumSecurityDelays),
			NumLateAircraftDelays: int32(r.NumLateAircraftDelays),
			TotalDelays:           int32(r.TotalDelays),
			CarrierDelayRate:      r.CarrierDelayRate,
			WeatherDelayRate:      r.WeatherDelayRate,
			NASDelayRate:          r.NASDelayRate,
			SecurityDelayRate:     r.SecurityDelayRate,
			LateAircraftDelayRate: r.LateAircraftDelayRate,
		})
	}
	return &proto.MainDelayReasonRateList{Items: pbResults}, nil
}

func main() {
	dsn := "sql_key_string"
	db, err := gorm.Open(mysql.Open(dsn), &gorm.Config{})
	if err != nil {
		panic("failed to connect database")
	}
	lis, _ := net.Listen("tcp", ":18080")
	println("server start at port 18080")
	s := grpc.NewServer()
	proto.RegisterFlightServiceServer(s, &server{db: db})
	s.Serve(lis)
}
